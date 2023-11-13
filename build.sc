import mill._

def mainClass: T[Option[String]] = Some("foo.Foo")

def swiftProjectLocation = millSourcePath / "Scala-Native-SwiftUI"
def xcFrameworkLocation = swiftProjectLocation / "Scala.xcframework"

def scalaCodePath = millSourcePath / "scala-code"
def scalaCode = T.source(scalaCodePath)
def generatedScalaCode = millSourcePath / "scala-code" / "generated"

def headers = T.source(millSourcePath / "headers")
def binaryInterfaceHeader = T.source(headers().path / "binary_interface.h")

def proto = T.source(millSourcePath / "protocol.proto")

def run(args: String*) = {
  System.err.println(s"Running ${args.mkString(" ")}")
  os.proc(args)
}

def buildMacos = T {
  generateSwiftProto()
  val fw = createXCFramework()
  os.makeDir.all(xcFrameworkLocation)
  os.copy.over(fw, xcFrameworkLocation)
}

def staticLib = T { buildLibrary() }
def bundledLib = T { bundleLibraries() }

def scalaSources = T {
  os.walk(scalaCode().path)
    .filter(_.ext == "scala")
    .filterNot { path =>
      path.toString.contains(".metals/") || path.toString
        .contains(".scala-build/")
    }
    .map(PathRef(_))
}

def coursierInstallDir = T {
  PathRef(T.dest)
}

def installBindgen = T {
  val dir = coursierInstallDir().path

  run("cs", "install", "sn-bindgen", "--contrib", "--dir", dir.toString)
    .call(stderr = os.Pipe)

  PathRef(dir / "sn-bindgen")
}

def allDeps = T { List("curl", "libidn2") }
def libraryList = T {
  List("curl", "z", "idn2", "unistring")

}

def createXCFramework = T {
  val out = T.dest / "Scala.xcframework"
  run(
    "xcodebuild",
    "-create-xcframework",
    "-library",
    bundledLib().path.toString,
    "-headers",
    headers().path.toString,
    "-output",
    out.toString
  ).call(stderr = os.Pipe)

  out
}

def bundleLibraries = T.task {
  val scalaLib = staticLib()
  val outLibrary = T.dest / "scala.bundled.a"

  val args = List(
    "cs",
    "launch",
    "com.indoorvivants.vcpkg:sn-vcpkg_3:dev",
    "--",
    "install"
  ) ++ allDeps() ++ List("--rename", "curl=libcurl", "-l")

  val location = run(args: _*)
    .call()
    .out
    .lines()
    .filter(_.startsWith("-L"))
    .head
    .stripPrefix("-L")

  val locationPath = os.Path(location)

  val libs =
    for (lib <- libraryList()) yield locationPath / s"lib$lib.a"

  val argsLibtool =
    List(
      "libtool",
      "-static",
      "-o",
      outLibrary.toString,
      staticLib().path.toString
    ) ++ libs.map(_.toString)

  run(argsLibtool: _*).call(stderr = os.Pipe)

  PathRef(outLibrary)

}

def buildLibrary = T {
  val library = T.dest / "scala.a"

  val baseSources = scalaSources()
  val protoSources = generateScalaProto()
  val bindingSources = generateBindings()

  val allSources = (baseSources ++ protoSources ++ bindingSources).distinct

  val args = List(
    "cs",
    "launch",
    s"com.indoorvivants.vcpkg:sn-vcpkg_3:dev",
    "--",
    "scala-cli",
    "--rename",
    "curl=libcurl",
    "curl",
    "--",
    "package"
  ) ++ allSources.map(_.path.toString) ++ List(
    ".",
    "-f",
    "-o",
    library.toString,
    "--native",
    "--native-target",
    "static"
  )
  run(
    args: _*
  ).call(cwd = scalaCodePath)

  PathRef(library)
}

def generateScalaProto = T {

  val scalaOut = generatedScalaCode

  val protoFile = proto().path

  run(
    "protoc",
    s"--scala_out=${scalaOut}",
    s"--proto_path=${protoFile / os.up}",
    protoFile.toString
  ).call(stderr = os.Pipe)

  os.walk(scalaOut / "protocol").toList.map(PathRef(_))
}

def generateSwiftProto = T {

  val swiftOut = swiftProjectLocation

  val protoFile = proto().path

  run(
    "protoc",
    s"--swift_out=${swiftOut}",
    s"--proto_path=${protoFile / os.up}",
    protoFile.toString
  ).call(stderr = os.Pipe)

  List(swiftOut / "protocol.pb.swift")
}

def generateBindings = T {
  val headerFile = binaryInterfaceHeader()
  val dest = generatedScalaCode / "binary_interface.scala"
  val bindgen = installBindgen().path

  val args = List(
    bindgen.toString,
    "--header",
    headerFile.path.toString,
    "--package",
    "scala_app.binarybridge",
    "--scala",
    "--clang",
    "-DSN_SKIP_INIT",
    "--render.no-location",
    "--export",
    "--out",
    dest.toString
  )

  run(
    args: _*
  )
    .call(stderr = os.Pipe)

  List(dest).map(PathRef(_))

}
