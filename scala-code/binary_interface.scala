package scala_app.binarybridge

import _root_.scala.scalanative.unsafe.*
import _root_.scala.scalanative.unsigned.*
import _root_.scala.scalanative.libc.*
import _root_.scala.scalanative.*

object structs:
  import _root_.scala_app.binarybridge.structs.*

  /**
  */
  opaque type ByteArray = CStruct2[CInt, CString]
  object ByteArray:
    given _tag: Tag[ByteArray] = Tag.materializeCStruct2Tag[CInt, CString]
    def apply()(using Zone): Ptr[ByteArray] = scala.scalanative.unsafe.alloc[ByteArray](1)
    def apply(size : CInt, bytes : CString)(using Zone): Ptr[ByteArray] = 
      val ____ptr = apply()
      (!____ptr).size = size
      (!____ptr).bytes = bytes
      ____ptr
    extension (struct: ByteArray)
      def size : CInt = struct._1
      def size_=(value: CInt): Unit = !struct.at1 = value
      def bytes : CString = struct._2
      def bytes_=(value: CString): Unit = !struct.at2 = value

  /**
  */
  opaque type Result = CStruct3[Ptr[ByteArray], CInt, Boolean]
  object Result:
    given _tag: Tag[Result] = Tag.materializeCStruct3Tag[Ptr[ByteArray], CInt, Boolean]
    def apply()(using Zone): Ptr[Result] = scala.scalanative.unsafe.alloc[Result](1)
    def apply(message : Ptr[ByteArray], id : CInt, isError : Boolean)(using Zone): Ptr[Result] = 
      val ____ptr = apply()
      (!____ptr).message = message
      (!____ptr).id = id
      (!____ptr).isError = isError
      ____ptr
    extension (struct: Result)
      def message : Ptr[ByteArray] = struct._1
      def message_=(value: Ptr[ByteArray]): Unit = !struct.at1 = value
      def id : CInt = struct._2
      def id_=(value: CInt): Unit = !struct.at2 = value
      def isError : Boolean = struct._3
      def isError_=(value: Boolean): Unit = !struct.at3 = value

trait ExportedFunctions:
  import _root_.scala_app.binarybridge.structs.*

  /**
  */
  def scala_app_free_result(result : Ptr[Result]): Boolean

  /**
  */
  def scala_app_init(start_state : Ptr[ByteArray], options : Ptr[ByteArray]): Ptr[Result]

  /**
  */
  def scala_app_request(message : Ptr[ByteArray]): Ptr[Result]

  /**
  */
  def scala_app_result_ok(result : Ptr[Result]): Boolean


object functions extends ExportedFunctions:
  import _root_.scala_app.binarybridge.structs.*

  /**
  */
  @exported
  override def scala_app_free_result(result : Ptr[Result]): Boolean = scala_app.binarybridge.impl.Implementations.scala_app_free_result(result)

  /**
  */
  @exported
  override def scala_app_init(start_state : Ptr[ByteArray], options : Ptr[ByteArray]): Ptr[Result] = scala_app.binarybridge.impl.Implementations.scala_app_init(start_state, options)

  /**
  */
  @exported
  override def scala_app_request(message : Ptr[ByteArray]): Ptr[Result] = scala_app.binarybridge.impl.Implementations.scala_app_request(message)

  /**
  */
  @exported
  override def scala_app_result_ok(result : Ptr[Result]): Boolean = scala_app.binarybridge.impl.Implementations.scala_app_result_ok(result)

object types:
  export _root_.scala_app.binarybridge.structs.*

object all:
  export _root_.scala_app.binarybridge.structs.ByteArray
  export _root_.scala_app.binarybridge.structs.Result
