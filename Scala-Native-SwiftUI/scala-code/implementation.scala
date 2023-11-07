package scala_app
package binarybridge
package impl

import binarybridge.all.*

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
import scala.util.control.NonFatal
import scribe.Level

object Implementations extends ExportedFunctions:

  override def scala_app_init(
      start_state_ptr: Ptr[ByteArray],
      options_ptr: Ptr[ByteArray]
  ): Ptr[Result] =
    safe:

      val options = Option.when(options_ptr != null)(read(Options, options_ptr))

      val minLevel = options
        .map(o => if o.debugLogging then Level.Debug else Level.Info)
        .getOrElse(Level.Info)

      scribe.Logger.root
        .clearHandlers()
        .withHandler(writer = scribe.writer.SystemErrWriter)
        .withMinimumLevel(minLevel)
        .replace()

      if start_state_ptr == null then state = Some(StateManager(State()))
      else state = Some(StateManager(read(State, start_state_ptr)))

      null

  end scala_app_init

  override def scala_app_free_result(res: Ptr[Result]) =
    if res != null then
      val id = (!res).id
      DangerZone.free(DangerZone.Token.fromInt(id))
    else true

  override def scala_app_request(
      message: Ptr[ByteArray]
  ): Ptr[Result] =
    safe:
      boundary:
        DangerZone.useWithToken: token =>

          val msg = read(protocol.Request, message)

          val result = handleRequest(
            state.getOrElse(break(makeError("State is not set!", token))),
            msg
          )

          result match
            case Answer.Error(msg) =>
              makeError(msg, token)
            case Answer.Ok(value) =>
              val byteArr = write(value)

              ret(byteArr, token)
  end scala_app_request

  override def scala_app_result_ok(result: Ptr[Result]): Boolean =
    result == null || !(!result).isError

  inline def safe(f: => Ptr[Result]): Ptr[Result] =
    try f
    catch
      case NonFatal(exc) =>
        DangerZone.useWithToken: token =>
          scribe.error(exc)
          makeError(exc.getMessage(), token)

  private var state = Option.empty[StateManager]

  private inline def ret(message: Ptr[ByteArray], id: DangerZone.Token)(using
      DangerZone
  ) =
    val result = alloc[scala_app.binarybridge.all.Result]()
    (!result).message = message
    (!result).isError = false
    (!result).id = id.asInstanceOf
    result

  private inline def makeError(msg: String, id: DangerZone.Token)(using
      DangerZone
  ): Ptr[Result] =
    val result = alloc[Result]()
    (!result).message = write(Error(msg))
    (!result).isError = true
    (!result).id = id.asInstanceOf
    result

  private inline def write[Msg <: scalapb.GeneratedMessage](
      msg: Msg
  )(using DangerZone) =
    val arr = msg.toByteArray
    val start = arr.atUnsafe(0)
    val len = arr.length

    val bytes = alloc[Byte](len)
    val byteArray = alloc[ByteArray](1)
    (!byteArray).size = len
    (!byteArray).bytes = bytes
    libc.memcpy(bytes, start, len.toUInt)
    byteArray
  end write

  private inline def read[A <: scalapb.GeneratedMessage](
      comp: GeneratedMessageCompanion[A],
      ptr: Ptr[ByteArray]
  ): A =
    Zone { implicit z =>
      val ar = new Array[Byte]((!ptr).size)
      libc.memcpy(ar.atUnsafe(0), (!ptr).bytes, ar.length.toUInt)

      comp.parseFrom(ar)
    }

end Implementations
