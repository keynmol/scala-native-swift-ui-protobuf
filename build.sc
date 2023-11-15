import mill._

def NAME = "Scala-Native-SwiftUI"

def mainClass: T[Option[String]] = Some("foo.Foo")

def swiftProjectLocation = millSourcePath / NAME
def xcFrameworkLocation = swiftProjectLocation / "Scala.xcframework"

def scalaCodePath = millSourcePath / "scala-code"
def scalaCode = T.source(scalaCodePath)
def generatedScalaCode = millSourcePath / "scala-code" / "generated"

def headers = T.source(millSourcePath / "headers")
def binaryInterfaceHeader = T.source(headers().path / "binary_interface.h")

def macosAppLocation = T.source(millSourcePath / "build" / s"$NAME.app")
def proto = T.source(millSourcePath / "protocol.proto")

def run(args: String*) = {
  System.err.println(s"Running ${args.mkString(" ")}")
  os.proc(args)
}

def runSanitise(values: Seq[String], args: String*) = {
  var raw = args.mkString(" ")
  values.foreach { v =>
    raw = raw.replace(v, "***")
  }
  System.err.println(s"Running ${raw}")
  os.proc(args)
}

def buildMacos = T {
  val pathToApp = xcodeBuild()
  val loc = macosAppLocation()

  os.copy.over(pathToApp.path, loc.path, createFolders = true)

  PathRef(loc.path)
}

def codeSignMacos = T {

  val app = buildMacos()

  val certEnvName = "MACOS_CERTIFICATE"
  val macosCertificate = T.env.get(certEnvName)
  val certPwdEnvName = "MACOS_CERTIFICATE_PWD"
  val macosCertificatePassword = T.env.get(certPwdEnvName)
  val identityEnvName = "MACOS_CERTIFICATE_IDENTITY"
  val macosCertificateIdentity = T.env.get(identityEnvName)

  if (
    macosCertificate.isEmpty || macosCertificatePassword.isEmpty || macosCertificateIdentity.isEmpty
  ) {
    T.log.error(
      s"$certEnvName or $certPwdEnvName or $identityEnvName env variables are missing, assuming no need to sign the app"
    )
  } else {

    val certificate = T.dest / "certificate.p12"
    os.write.over(
      certificate,
      java.util.Base64.getDecoder.decode(macosCertificate.get.getBytes())
    )

    val randomPassword = scala.util.Random.alphanumeric.take(25).toList.mkString

    runSanitise(
      Seq(randomPassword),
      "security",
      "create-keychain",
      "-p",
      randomPassword,
      "build.keychain"
    ).call()

    run(
      "security",
      "default-keychain",
      "-s",
      "build.keychain"
    ).call(stderr = os.Pipe, stdout = os.Pipe)

    runSanitise(
      Seq(randomPassword),
      "security",
      "unlock-keychain",
      "-p",
      randomPassword,
      "build.keychain"
    ).call(stderr = os.Pipe, stdout = os.Pipe)

    runSanitise(
      Seq(macosCertificatePassword.get),
      "security",
      "import",
      certificate.toString,
      "-k",
      "build.keychain",
      "-P",
      macosCertificatePassword.get,
      "-T",
      "/usr/bin/codesign"
    ).call(stderr = os.Pipe, stdout = os.Pipe)
    runSanitise(
      Seq(randomPassword),
      "security",
      "set-key-partition-list",
      "-S",
      "apple-tool:,apple:,codesign:",
      "-s",
      "-k",
      randomPassword,
      "build.keychain"
    ).call(stderr = os.Pipe, stdout = os.Pipe)

    run("security", "find-identity").call(stderr = os.Pipe, stdout = os.Pipe)

    run(
      "/usr/bin/codesign",
      "--force",
      "-s",
      macosCertificateIdentity.get,
      app.path.toString,
      "-v"
    ).call(stderr = os.Pipe, stdout = os.Pipe)

  }
  ()
}

def zipMacos = T {
  val pathToApp = buildMacos()
  codeSignMacos()

  val zip = T.dest / s"$NAME.app.zip"

  run(
    "zip",
    "-r",
    zip.toString(),
    s"$NAME.app"
  ).call(cwd = pathToApp.path / os.up)

  val finalDestination = millSourcePath / "build" / s"$NAME.app.zip"
  os.copy.over(zip, finalDestination, createFolders = true)

  PathRef(finalDestination)

}

def xcProject = T.source(millSourcePath / s"$NAME.xcproject")

def moveXCFramework = T {

  val fw = createXCFramework()

  os.makeDir.all(xcFrameworkLocation)
  os.copy.over(fw.path, xcFrameworkLocation)
}

def xcodeBuild = T {

  generateSwiftProto()
  swiftSources()
  moveXCFramework()
  xcProject()

  val destination = T.dest / NAME
  val args = List(
    "xcodebuild",
    "archive",
    "-archivePath",
    destination.toString,
    "-scheme",
    NAME
  )

  run(args: _*).call(stderr = os.Pipe, stdout = os.Pipe)

  PathRef(
    T.dest / s"$NAME.xcarchive" / "Products" / "Applications" / s"$NAME.app"
  )

}

def staticLib = T { buildLibrary() }
def bundledLib = T { bundleLibraries() }

def swiftSources = T.source(swiftProjectLocation)

def scalaSources = T {
  os.walk(
    scalaCode().path,
    skip = path => {
      path.lastOpt.exists(m => m == ".metals" || m == ".scala-build")
    }
  ).filter(_.ext == "scala")
    .map(PathRef(_))
}

def coursierLauncherPath = T {
  val path = T.dest / "coursier"
  os.write.over(
    path,
    requests.get.stream(
      "https://github.com/coursier/launchers/raw/master/coursier"
    )
  )

  os.proc("chmod", "+x", path.toString()).call()

  PathRef(path)
}

def coursierInstallDir = T {
  PathRef(T.dest)
}

def installBindgen = T {
  val dir = coursierInstallDir().path

  run(
    coursierLauncherPath().path.toString,
    "install",
    "sn-bindgen",
    "--contrib",
    "--dir",
    dir.toString
  )
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

  PathRef(out)
}

def bundleLibraries = T.task {
  val scalaLib = staticLib()
  val outLibrary = T.dest / "scala.bundled.a"

  val args = List(
    coursierLauncherPath().path.toString,
    "launch",
    "sn-vcpkg",
    "--contrib",
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
    coursierLauncherPath().path.toString,
    "launch",
    "scala-cli:1.0.5",
    "--",
    "--power",
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
