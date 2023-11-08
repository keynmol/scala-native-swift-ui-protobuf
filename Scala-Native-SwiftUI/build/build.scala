//> using dep com.lihaoyi::os-lib::0.9.1
//> using dep com.outr::scribe::3.12.2

val SN_VCPKG_RELEASE = "dev"

@main def build(path: String) =
  val dest = os.Path(path, os.pwd)

  val xcFrameworkLocation = dest / "Scala.xcframework"
  val headers = dest / "headers"
  val header = headers / "binary_interface.h"
  val scalaCode = dest / "scala-code"
  val implementation = scalaCode / "implementation.scala"
  val bindings = scalaCode / "binary_interface.scala"
  val scalaCodeLibrary = scalaCode / "scala_interface.a"
  val protoFile = dest / "protocol.proto"
  val protoScalabindings = scalaCode / "generated"
  val bundledLibrary = scalaCode / "bundled_scala_interface.a"

  os.makeDir.all(xcFrameworkLocation)
  generateBindings(header, bindings)
  generateProtobuf(protoFile, dest, protoScalabindings)
  buildLibrary(scalaCode, scalaCodeLibrary)
  bundleLibraries(scalaCodeLibrary, bundledLibrary)
  createXCFramework(bundledLibrary, headers, xcFrameworkLocation)
end build

def run(args: String*) =
  scribe.info("Running", args.mkString(" "))
  os.proc(args)

def buildLibrary(
    scalaCode: os.Path,
    library: os.Path
) =
  run(
    "cs",
    "launch",
    "com.indoorvivants.vcpkg:sn-vcpkg_3:dev",
    "--",
    "scala-cli",
    "--rename",
    "curl=libcurl",
    "curl",
    "--",
    "package",
    ".",
    "-f",
    "-o",
    library.toString,
    "--native",
    "--native-target",
    "static"
  ).call(cwd = scalaCode)

def bundleLibraries(scalaCodeLibrary: os.Path, outLibrary: os.Path) =
  val libraryList = List("curl", "z", "idn2", "unistring")
  val location = run(
    "cs",
    "launch",
    "com.indoorvivants.vcpkg:sn-vcpkg_3:dev",
    "--",
    "install",
    "curl",
    "--rename",
    "curl=libcurl",
    "-l"
  ).call().out.lines().filter(_.startsWith("-L")).head.stripPrefix("-L")

  val locationPath = os.Path(location)

  val libs =
    for lib <- libraryList yield locationPath / s"lib$lib.a"

  val args =
    List(
      "libtool",
      "-static",
      "-o",
      outLibrary.toString,
      scalaCodeLibrary.toString
    ) ++ libs.map(_.toString)

  run(args*).call(stderr = os.Pipe)

end bundleLibraries

def generateProtobuf(protoFile: os.Path, swiftOut: os.Path, scalaOut: os.Path) =
  os.makeDir.all(scalaOut)
  run(
    "protoc",
    s"--swift_out=${swiftOut}",
    s"--scala_out=${scalaOut}",
    s"--proto_path=${protoFile / os.up}",
    protoFile.toString
  ).call(stderr = os.Pipe)
end generateProtobuf

def generateBindings(header: os.Path, out: os.Path) =
  run(
    "bindgen",
    "--header",
    header.toString,
    "--package",
    "scala_app.binarybridge",
    "--scala",
    "--clang",
    "-DSN_SKIP_INIT",
    "--export",
    "--out",
    out.toString
  ).call()

def createXCFramework(staticLib: os.Path, header: os.Path, out: os.Path) =
  os.remove.all(out)
  run(
    "xcodebuild",
    "-create-xcframework",
    "-library",
    staticLib.toString,
    "-headers",
    header.toString,
    "-output",
    out.toString
  ).call()
end createXCFramework
