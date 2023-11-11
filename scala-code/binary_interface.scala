package scala_app.binarybridge

import _root_.scala.scalanative.unsafe.*
import _root_.scala.scalanative.unsigned.*
import _root_.scala.scalanative.libc.*
import _root_.scala.scalanative.*

object aliases:
  import _root_.scala_app.binarybridge.aliases.*

  /**
  */
  opaque type ScalaResult = Ptr[Byte]
  object ScalaResult: 
    given _tag: Tag[ScalaResult] = Tag.Ptr(Tag.Byte)
    inline def apply(inline o: Ptr[Byte]): ScalaResult = o
    extension (v: ScalaResult)
      inline def value: Ptr[Byte] = v

trait ExportedFunctions:
  import _root_.scala_app.binarybridge.aliases.*

  /**
  */
  def scala_app_free_result(result : ScalaResult): Boolean

  /**
  */
  def scala_app_get_data(result : ScalaResult): CString

  /**
  */
  def scala_app_get_data_length(result : ScalaResult): CInt

  /**
  */
  def scala_app_request(data : CString, data_len : CInt): ScalaResult

  /**
  */
  def scala_app_result_ok(result : ScalaResult): Boolean


object functions extends ExportedFunctions:
  import _root_.scala_app.binarybridge.aliases.*

  /**
  */
  @exported
  override def scala_app_free_result(result : ScalaResult): Boolean = scala_app.binarybridge.impl.Implementations.scala_app_free_result(result)

  /**
  */
  @exported
  override def scala_app_get_data(result : ScalaResult): CString = scala_app.binarybridge.impl.Implementations.scala_app_get_data(result)

  /**
  */
  @exported
  override def scala_app_get_data_length(result : ScalaResult): CInt = scala_app.binarybridge.impl.Implementations.scala_app_get_data_length(result)

  /**
  */
  @exported
  override def scala_app_request(data : CString, data_len : CInt): ScalaResult = scala_app.binarybridge.impl.Implementations.scala_app_request(data, data_len)

  /**
  */
  @exported
  override def scala_app_result_ok(result : ScalaResult): Boolean = scala_app.binarybridge.impl.Implementations.scala_app_result_ok(result)

object types:
  export _root_.scala_app.binarybridge.aliases.*

object all:
  export _root_.scala_app.binarybridge.aliases.ScalaResult
