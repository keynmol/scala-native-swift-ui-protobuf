package scala_app
package binarybridge
package impl

import _root_.protocol.*
import scala_app.logic.StateManager
import scala_app.logic.*
import scalapb.GeneratedMessage
import scalapb.GeneratedMessageCompanion
import scribe.Level

import java.io.ByteArrayOutputStream
import scala.scalanative.runtime.Intrinsics
import scala.scalanative.runtime.libc
import scala.scalanative.unsafe.*
import scala.scalanative.unsigned.*
import scala.util.boundary
import scala.util.boundary.Label
import scala.util.control.NonFatal

import binarybridge.all.*
import boundary.break
import scala_app.binarybridge.impl.Implementations.GCRoots

object Implementations extends ExportedFunctions:
  def scala_app_get_error(
      result: scala_app.binarybridge.aliases.ScalaResult
  ): scala.scalanative.unsafe.CString =
    toByteArray(result).atUnsafe(1)

  def scala_app_get_error_length(
      result: scala_app.binarybridge.aliases.ScalaResult
  ): scala.scalanative.unsafe.CInt =
    toByteArray(result).length - 1

  def scala_app_get_response(
      result: scala_app.binarybridge.aliases.ScalaResult
  ): scala.scalanative.unsafe.CString =
    toByteArray(result).atUnsafe(1)

  def scala_app_get_response_length(
      result: scala_app.binarybridge.aliases.ScalaResult
  ): scala.scalanative.unsafe.CInt =
    toByteArray(result).length - 1

  def scala_app_is_error(
      result: scala_app.binarybridge.aliases.ScalaResult
  ): Boolean =
    toByteArray(result)(0) == 1

  def scala_app_is_response(
      result: scala_app.binarybridge.aliases.ScalaResult
  ): Boolean =
    toByteArray(result)(0) == 0

  private def toByteArray(res: ScalaResult) =
    val arr = Intrinsics
      .castRawPtrToObject(scalanative.runtime.toRawPtr(res.value))
      .asInstanceOf[Array[Byte]]
    println(s"$res: ${arr.toList}")
    arr

  override def scala_app_free_result(res: ScalaResult) =
    if res.value != null then
      val obj =
        Intrinsics.castRawPtrToObject(scalanative.runtime.toRawPtr(res.value))

      if GCRoots.hasRoot(obj) then
        GCRoots.removeRoot(obj)
        true
      else false
    else true

  val state = StateManager(State())

  override def scala_app_request(
      data: Ptr[Byte],
      data_len: Int
  ): ScalaResult =
    safe:
      val msg = read(protocol.Request, data, data_len)

      handleRequest(state, msg) match
        case Answer.Error(msg, code) =>
          makeError(msg, code)()
        case Answer.Ok(value) =>
          makeResult(value)()
      end match
  end scala_app_request

  inline def safe[A](f: => ScalaResult): ScalaResult =
    try f
    catch
      case NonFatal(exc) =>
        scribe.error(exc)
        makeError(exc.getMessage(), ERROR_CODE.OTHER)()

  private inline def makeResult[T <: scalapb.GeneratedMessage](msg: T) =
    val marshalled = write(msg, false)
    val arrPtr = scalanative.runtime.fromRawPtr[Byte](
      Intrinsics.castObjectToRawPtr(marshalled)
    )

    () =>
      GCRoots.addRoot(marshalled)
      ScalaResult(arrPtr)
  end makeResult

  private inline def makeError(
      msg: String,
      code: ERROR_CODE
  ): () => ScalaResult =
    val marshalled = write(Error(code, msg), true)
    val arrPtr = scalanative.runtime.fromRawPtr[Byte](
      Intrinsics.castObjectToRawPtr(marshalled)
    )

    println(marshalled.toList)

    () =>
      GCRoots.addRoot(marshalled)
      ScalaResult(arrPtr)
  end makeError

  private inline def write[Msg <: scalapb.GeneratedMessage](
      msg: Msg,
      isError: Boolean
  ) =
    val ar = new ByteArrayOutputStream(
      msg.serializedSize + 1
    )

    ar.write(if isError then 1 else 0)

    msg.writeTo(ar)

    ar.toByteArray()

  end write

  private[binarybridge] inline def read[A <: scalapb.GeneratedMessage](
      comp: GeneratedMessageCompanion[A],
      data: Ptr[Byte],
      len: Int
  ): A =
    Zone { implicit z =>
      // TODO: In Scala Native 0.5 we can avoid this copying by using PointerByteBuffer
      val ar = new Array[Byte](len)
      libc.memcpy(ar.atUnsafe(0), data, ar.length.toUInt)

      comp.parseFrom(ar)
    }

  object GCRoots:
    private val references = new java.util.IdentityHashMap[Object, Unit]
    def addRoot(o: Object): Unit = references.put(o, ())
    def removeRoot(o: Object): Unit = references.remove(o)
    def hasRoot(o: Object): Boolean = references.containsKey(o)

    override def toString(): String = references.toString

end Implementations

// @main def hello =
//   val msg = SetOptions().toByteArray

//   val result = Implementations.scala_app_request(msg.atUnsafe(0), msg.length)

//   println(Implementations.scala_app_is_error(result))

//   println(result.value(0))
//   // println(Implementations.scala_app_get_error_length(result))
//   // println(Implementations.scala_app_get_error(result))

//   println(
//     Implementations.read(
//       Error,
//       Implementations.scala_app_get_error(result),
//       Implementations.scala_app_get_error_length(result)
//     )
//   )

//   println(GCRoots)
// end hello
