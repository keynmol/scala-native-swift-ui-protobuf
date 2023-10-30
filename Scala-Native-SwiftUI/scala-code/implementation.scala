package scala_app.binarybridge
package impl

import all.*
import scala.scalanative.unsafe.*
import scala.scalanative.unsigned.*

import scala_app.messages.*
import scala.scalanative.runtime.libc
import scalapb.GeneratedMessage
import scalapb.GeneratedMessageCompanion
import scala_app.messages.protocol.Exchange

inline def write[Msg <: scalapb.GeneratedMessage](msg: Msg, ptr: Ptr[ByteArray]) = 
  val arr = msg.toByteArray
  val start = arr.atUnsafe(0)
  val len = arr.length

  (!ptr).size = len
  libc.memcpy((!ptr).bytes, start, len.toUInt)

inline def read[A <: scalapb.GeneratedMessage](comp: GeneratedMessageCompanion[A], ptr: Ptr[ByteArray]) = 
  scala.util.Try: 
    Zone{ implicit z =>
      val ar = new Array[Byte]((!ptr).size)
      libc.memcpy(ar.atUnsafe(0), (!ptr).bytes, ar.length.toUInt)

      comp.parseFrom(ar)
    }

object Implementations extends ExportedFunctions:
  def scala_app_notification(message: Ptr[ByteArray]): Unit = 
    val msg = read(Exchange, message)
    println(msg)

  def scala_app_request(
      message: Ptr[ByteArray],
      response_holder: Ptr[ByteArray]
  ): Unit = 
    val msg = read(Exchange, message)
    println(msg)
