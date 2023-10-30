package scala_app.binarybridge

import _root_.scala.scalanative.unsafe.*
import _root_.scala.scalanative.unsigned.*
import _root_.scala.scalanative.libc.*
import _root_.scala.scalanative.*

object structs:
  import _root_.scala_app.binarybridge.structs.*

  /**
   * [bindgen] header: /Users/velvetbaldmime/projects/Scala-Native-SwiftUI/Scala-Native-SwiftUI/headers/binary_interface.h
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

trait ExportedFunctions:
  import _root_.scala_app.binarybridge.structs.*

  /**
   * [bindgen] header: /Users/velvetbaldmime/projects/Scala-Native-SwiftUI/Scala-Native-SwiftUI/headers/binary_interface.h
  */
  def scala_app_notification(message : Ptr[ByteArray]): Unit

  /**
   * [bindgen] header: /Users/velvetbaldmime/projects/Scala-Native-SwiftUI/Scala-Native-SwiftUI/headers/binary_interface.h
  */
  def scala_app_request(message : Ptr[ByteArray], response_holder : Ptr[ByteArray]): Unit


object functions extends ExportedFunctions:
  import _root_.scala_app.binarybridge.structs.*

  /**
   * [bindgen] header: /Users/velvetbaldmime/projects/Scala-Native-SwiftUI/Scala-Native-SwiftUI/headers/binary_interface.h
  */
  @exported
  override def scala_app_notification(message : Ptr[ByteArray]): Unit = scala_app.binarybridge.impl.Implementations.scala_app_notification(message)

  /**
   * [bindgen] header: /Users/velvetbaldmime/projects/Scala-Native-SwiftUI/Scala-Native-SwiftUI/headers/binary_interface.h
  */
  @exported
  override def scala_app_request(message : Ptr[ByteArray], response_holder : Ptr[ByteArray]): Unit = scala_app.binarybridge.impl.Implementations.scala_app_request(message, response_holder)

object types:
  export _root_.scala_app.binarybridge.structs.*

object all:
  export _root_.scala_app.binarybridge.structs.ByteArray
