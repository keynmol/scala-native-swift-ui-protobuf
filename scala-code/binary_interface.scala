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
  def scala_app_get_error(result : ScalaResult): CString

  /**
  */
  def scala_app_get_error_length(result : ScalaResult): CInt

  /**
  */
  def scala_app_get_response(result : ScalaResult): CString

  /**
  */
  def scala_app_get_response_length(result : ScalaResult): CInt

  /**
  */
  def scala_app_is_error(result : ScalaResult): Boolean

  /**
  */
  def scala_app_is_response(result : ScalaResult): Boolean

  /**
  */
  def scala_app_request(data : CString, data_len : CInt): ScalaResult


object functions extends ExportedFunctions:
  import _root_.scala_app.binarybridge.aliases.*

  /**
  */
  @exported
  override def scala_app_free_result(result : ScalaResult): Boolean = scala_app.binarybridge.impl.Implementations.scala_app_free_result(result)

  /**
  */
  @exported
  override def scala_app_get_error(result : ScalaResult): CString = scala_app.binarybridge.impl.Implementations.scala_app_get_error(result)

  /**
  */
  @exported
  override def scala_app_get_error_length(result : ScalaResult): CInt = scala_app.binarybridge.impl.Implementations.scala_app_get_error_length(result)

  /**
  */
  @exported
  override def scala_app_get_response(result : ScalaResult): CString = scala_app.binarybridge.impl.Implementations.scala_app_get_response(result)

  /**
  */
  @exported
  override def scala_app_get_response_length(result : ScalaResult): CInt = scala_app.binarybridge.impl.Implementations.scala_app_get_response_length(result)

  /**
  */
  @exported
  override def scala_app_is_error(result : ScalaResult): Boolean = scala_app.binarybridge.impl.Implementations.scala_app_is_error(result)

  /**
  */
  @exported
  override def scala_app_is_response(result : ScalaResult): Boolean = scala_app.binarybridge.impl.Implementations.scala_app_is_response(result)

  /**
  */
  @exported
  override def scala_app_request(data : CString, data_len : CInt): ScalaResult = scala_app.binarybridge.impl.Implementations.scala_app_request(data, data_len)

object types:
  export _root_.scala_app.binarybridge.aliases.*

object all:
  export _root_.scala_app.binarybridge.aliases.ScalaResult
