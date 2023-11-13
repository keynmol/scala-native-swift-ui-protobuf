import mill._

def mainClass: T[Option[String]] = Some("foo.Foo")

// def sources = T.source(millSourcePath / "src")
def swiftProjectLocation = millSourcePath / "Scala-Native-SwiftUI"
def xcFrameworkLocation = swiftProjectLocation / "Scala.xcframework"

def scalaCode = millSourcePath / "scala-code"
def generatedScalaCode = millSourcePath / "scala-code" / "generated"

def headers = millSourcePath / "headers"
def binaryInterfaceHeader = T.source(headers / "binary_interface.h")

def proto = T.source(millSourcePath / "protocol.proto")

def generateProtos = T {

  val scalaOut = generatedScalaCode
  val swiftOut = swiftProjectLocation

  val protoFile = proto().path

  os.proc(
    "protoc",
    s"--swift_out=${swiftOut}",
    s"--scala_out=${scalaOut}",
    s"--proto_path=${protoFile / os.up}",
    protoFile.toString
  ).call(stderr = os.Pipe)
}

def regenerateBindings = T {
  val headerFile = binaryInterfaceHeader()
  val dest = generatedScalaCode / "binary_interface.scala"

  val args = List(
    "bindgen",
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

  os.proc(args).call(stderr = os.Pipe)

}

// def resources = T.source(millSourcePath / "resources")

def compile = T {
  // val allSources = os.walk(sources().path)
  println(millSourcePath)
  // os.proc("javac", allSources, "-d", T.dest).call()
  // PathRef(T.dest)
}

// def assembly = T {
//   for(p <- Seq(compile(), resources())) os.copy(p.path, T.dest, mergeFolders = true)

//   val mainFlags = mainClass().toSeq.flatMap(Seq("-e", _))
//   os.proc("jar", "-c", mainFlags, "-f", T.dest / s"assembly.jar", ".")
//     .call(cwd = T.dest)

//   PathRef(T.dest / s"assembly.jar")
// }
