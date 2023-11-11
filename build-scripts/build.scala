//> using dep com.lihaoyi::os-lib::0.9.2
//> using dep com.outr::scribe::3.12.2
//> using dep com.monovore::decline::2.4.1

import com.monovore.decline.*

val SN_VCPKG_RELEASE = "dev"

@main def build(root: String) =
  val repoRoot = os.Path(root, os.pwd)

  val swiftDestination = repoRoot / "Scala-Native-SwiftUI"
  val xcFrameworkLocation = swiftDestination / "Scala.xcframework"
  val headers = repoRoot / "headers"
  val header = headers / "binary_interface.h"
  val scalaCode = repoRoot / "scala-code"
  val implementation = scalaCode / "implementation.scala"
  val bindings = scalaCode / "binary_interface.scala"
  val scalaCodeLibrary = scalaCode / "scala_interface.a"
  val protoFile = repoRoot / "protocol.proto"
  val protoScalaOut = scalaCode / "generated"
  val protoSwiftOut = swiftDestination
  val bundledLibrary = scalaCode / "bundled_scala_interface.a"

  generateBindings(header, bindings)
  generateProtobuf(protoFile, protoSwiftOut, protoScalaOut)
  buildLibrary(scalaCode, scalaCodeLibrary)
  bundleLibraries(scalaCodeLibrary, bundledLibrary)
  createXCFramework(bundledLibrary, headers, xcFrameworkLocation)
end build

def run(args: String*) =
  scribe.info(s"Running ${args.mkString(" ")}")
  os.proc(args)

def buildLibrary(
    scalaCode: os.Path,
    library: os.Path
) =
  run(
    "cs",
    "launch",
    s"com.indoorvivants.vcpkg:sn-vcpkg_3:$SN_VCPKG_RELEASE",
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
    "libidn2",
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
    "--render.no-location",
    "--export",
    "--out",
    out.toString
  ).call()

def createXCFramework(staticLib: os.Path, header: os.Path, out: os.Path) =
  os.makeDir.all(out)
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
