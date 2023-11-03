//> using option -Xwarn:all

package scala_app.binarybridge
package impl

import all.*
import scala.scalanative.unsafe.*
import scala.scalanative.unsigned.*

import scala.scalanative.runtime.libc
import scalapb.GeneratedMessage
import scalapb.GeneratedMessageCompanion
import _root_.protocol.*
import scala_app.logic.StateManager
import scala.util.boundary, boundary.break
import scala.util.boundary.Label
import scala_app.logic.*
import scala.util.Try

def _alloc[T: Tag](n: Int = 1)(using al: Allocator): Ptr[T] =
  val bytes = sizeof[T] * n.toUInt
  println(s"Allocating ${bytes}")
  al.value(bytes).asInstanceOf[Ptr[T]]

inline def write[Msg <: scalapb.GeneratedMessage](
    msg: Msg
)(using Allocator) =
  val arr = msg.toByteArray
  val start = arr.atUnsafe(0)
  val len = arr.length

  println(s"Writing ${msg} as ${arr.toList}")

  val bytes = _alloc[Byte](len)
  val byteArray = _alloc[ByteArray](1)

  println(bytes)
  println(byteArray)

  (!byteArray).size = len
  (!byteArray).bytes = bytes
  libc.memcpy(bytes, start, len.toUInt)
  byteArray
end write

inline def read[A <: scalapb.GeneratedMessage](
    comp: GeneratedMessageCompanion[A],
    ptr: Ptr[ByteArray]
): Either[String, A] =
  scala.util
    .Try:
      Zone { implicit z =>
        val ar = new Array[Byte]((!ptr).size)
        libc.memcpy(ar.atUnsafe(0), (!ptr).bytes, ar.length.toUInt)

        comp.parseFrom(ar)
      }
    .toEither
    .left
    .map(_.getMessage())

object Implementations extends ExportedFunctions:

  override def scala_app_request(
      message: Ptr[ByteArray],
      context: Ptr[Context]
  ): Ptr[Result] =
    println(message)
    println(context)
    try
      boundary:
        withAllocator:
          val msg = read(protocol.Request, message)
            .fold(err => break(raiseError(err)), identity)

          println(msg)

          val result = handleRequest(
            state.getOrElse(break(raiseError("State is not set!"))),
            msg
          )
          println(result)

          result match
            case Answer.Error(msg) =>
              raiseError(msg)
            case Answer.Ok(value) =>
              val byteArr = write(value)
              println(byteArr)

              ret(byteArr)
    catch
      case exc =>
        println(exc)
        exc.printStackTrace()
        null
    end try
  end scala_app_request

  override def scala_app_result_ok(result: Ptr[Result]): Boolean =
    result == null || ((!result).error == null)

  var allocator = Option.empty[Allocator]
  var state = Option.empty[StateManager]

  val allocatorNotReady = c"Allocator is not initialised!"
  val stateNotReady = c"State is not initialised!"

  lazy val dangerZone = Zone.open()

  inline def ret(message: Ptr[ByteArray])(using Allocator) =
    val result = _alloc[scala_app.binarybridge.all.Result]()
    (!result).error = null
    (!result).message = message
    result

  inline def raiseError(msg: String)(using Allocator): Ptr[Result] =
    val result = _alloc[Result]()
    (!result).error = write(Error(msg))
    (!result).message = null
    result

  lazy val leakingAllocator = Allocator { (size: CSize) =>
    dangerZone.alloc(size)
  }

  def withAllocator[A](f: Allocator ?=> A)(using Label[Ptr[Result]]) =
    given Allocator =
      allocator.getOrElse(break(raiseError("Allocator not set!")))
    f

  def scala_app_init(
      start_state: Ptr[ByteArray],
      context: Ptr[Context]
  ): Ptr[Result] =
    boundary:
      println("what")
      // println(Try(raiseError("what")(using leakingAllocator)))
      if (!context).allocator.value == null then
        given Allocator = leakingAllocator
        break(raiseError("Allocator cannot be null"))
      else
        allocator = Some((!context).allocator)
        // TODO: init state
        if start_state == null then state = Some(StateManager(State()))

        null
      end if

  end scala_app_init

end Implementations
