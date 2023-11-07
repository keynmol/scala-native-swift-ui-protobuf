//> using dep com.lihaoyi::os-lib::0.9.1
//> using dep com.outr::scribe::3.12.2

@main def build(path: String) =
  val dest = os.Path(path, os.pwd)

  val xcFrameworkLocation = dest / "Scala.xcframework"
  val headers = dest / "headers"
  val header = headers / "binary_interface.h"
  val scalaCode = dest / "scala-code"
  val implementation = scalaCode / "implementation.scala"
  val bindings = scalaCode / "binary_interface.scala"
  val library = scalaCode / "scala_interface.a"
  val protoFile = dest / "protocol.proto"
  val protoScalabindings = scalaCode / "generated"

  os.makeDir.all(xcFrameworkLocation)
  generateBindings(header, bindings)
  generateProtobuf(protoFile, dest, protoScalabindings)
  buildLibrary(scalaCode, library)
  createXCFramework(library, headers, xcFrameworkLocation)


def run(args: String*) = 
  scribe.info("Running", args.mkString(" "))
  os.proc(args)


def buildLibrary(
    scalaCode: os.Path,
    library: os.Path
) =
  run(
    // "cs", "launch",
    // "com.indoorvivants.vcpkg:sn-vcpkg_3:dev",
    // "--",
    "scala-cli",
    // "-v",
    // "--rename",
    // "curl=libcurl",
    // "curl",
    // "--",
    "package",
    ".",
    "-f",
    "-o",
    library.toString,
    "--native",
    "--native-target",
    "static",
    "--native-linking",
    "-lcurl"
  ).call(cwd = scalaCode)

def generateProtobuf(protoFile: os.Path, swiftOut: os.Path, scalaOut: os.Path) = 
  os.makeDir.all(scalaOut)
  run(
    "protoc",
    s"--swift_out=${swiftOut}",
    s"--scala_out=${scalaOut}",
    s"--proto_path=${protoFile / os.up}",
    protoFile.toString
  ).call(stderr = os.Pipe)

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
