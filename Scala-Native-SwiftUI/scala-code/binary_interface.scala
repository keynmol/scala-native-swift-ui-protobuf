package scala_app.binarybridge

import _root_.scala.scalanative.unsafe.*
import _root_.scala.scalanative.unsigned.*
import _root_.scala.scalanative.libc.*
import _root_.scala.scalanative.*

object aliases:
  import _root_.scala_app.binarybridge.aliases.*
  import _root_.scala_app.binarybridge.structs.*

  /**
   * [bindgen] header: /Users/velvetbaldmime/projects/Scala-Native-SwiftUI/Scala-Native-SwiftUI/headers/binary_interface.h
  */
  opaque type Allocator = CFuncPtr1[size_t, Ptr[Byte]]
  object Allocator: 
    given _tag: Tag[Allocator] = Tag.materializeCFuncPtr1[size_t, Ptr[Byte]]
    inline def apply(inline o: CFuncPtr1[size_t, Ptr[Byte]]): Allocator = o
    extension (v: Allocator)
      inline def value: CFuncPtr1[size_t, Ptr[Byte]] = v

  type size_t = libc.stddef.size_t
  object size_t: 
    val _tag: Tag[size_t] = summon[Tag[libc.stddef.size_t]]
    inline def apply(inline o: libc.stddef.size_t): size_t = o
    extension (v: size_t)
      inline def value: libc.stddef.size_t = v

object structs:
  import _root_.scala_app.binarybridge.aliases.*
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

  /**
   * [bindgen] header: /Users/velvetbaldmime/projects/Scala-Native-SwiftUI/Scala-Native-SwiftUI/headers/binary_interface.h
  */
  opaque type Context = CStruct1[Allocator]
  object Context:
    given _tag: Tag[Context] = Tag.materializeCStruct1Tag[Allocator]
    def apply()(using Zone): Ptr[Context] = scala.scalanative.unsafe.alloc[Context](1)
    def apply(allocator : Allocator)(using Zone): Ptr[Context] = 
      val ____ptr = apply()
      (!____ptr).allocator = allocator
      ____ptr
    extension (struct: Context)
      def allocator : Allocator = struct._1
      def allocator_=(value: Allocator): Unit = !struct.at1 = value

  /**
   * [bindgen] header: /Users/velvetbaldmime/projects/Scala-Native-SwiftUI/Scala-Native-SwiftUI/headers/binary_interface.h
  */
  opaque type Result = CStruct2[Ptr[ByteArray], Ptr[ByteArray]]
  object Result:
    given _tag: Tag[Result] = Tag.materializeCStruct2Tag[Ptr[ByteArray], Ptr[ByteArray]]
    def apply()(using Zone): Ptr[Result] = scala.scalanative.unsafe.alloc[Result](1)
    def apply(message : Ptr[ByteArray], error : Ptr[ByteArray])(using Zone): Ptr[Result] = 
      val ____ptr = apply()
      (!____ptr).message = message
      (!____ptr).error = error
      ____ptr
    extension (struct: Result)
      def message : Ptr[ByteArray] = struct._1
      def message_=(value: Ptr[ByteArray]): Unit = !struct.at1 = value
      def error : Ptr[ByteArray] = struct._2
      def error_=(value: Ptr[ByteArray]): Unit = !struct.at2 = value

trait ExportedFunctions:
  import _root_.scala_app.binarybridge.aliases.*
  import _root_.scala_app.binarybridge.structs.*

  /**
   * [bindgen] header: /Users/velvetbaldmime/projects/Scala-Native-SwiftUI/Scala-Native-SwiftUI/headers/binary_interface.h
  */
  def scala_app_init(start_state : Ptr[ByteArray], context : Ptr[Context]): Ptr[Result]

  /**
   * [bindgen] header: /Users/velvetbaldmime/projects/Scala-Native-SwiftUI/Scala-Native-SwiftUI/headers/binary_interface.h
  */
  def scala_app_request(message : Ptr[ByteArray], context : Ptr[Context]): Ptr[Result]

  /**
   * [bindgen] header: /Users/velvetbaldmime/projects/Scala-Native-SwiftUI/Scala-Native-SwiftUI/headers/binary_interface.h
  */
  def scala_app_result_ok(result : Ptr[Result]): Boolean


object functions extends ExportedFunctions:
  import _root_.scala_app.binarybridge.aliases.*
  import _root_.scala_app.binarybridge.structs.*

  /**
   * [bindgen] header: /Users/velvetbaldmime/projects/Scala-Native-SwiftUI/Scala-Native-SwiftUI/headers/binary_interface.h
  */
  @exported
  override def scala_app_init(start_state : Ptr[ByteArray], context : Ptr[Context]): Ptr[Result] = scala_app.binarybridge.impl.Implementations.scala_app_init(start_state, context)

  /**
   * [bindgen] header: /Users/velvetbaldmime/projects/Scala-Native-SwiftUI/Scala-Native-SwiftUI/headers/binary_interface.h
  */
  @exported
  override def scala_app_request(message : Ptr[ByteArray], context : Ptr[Context]): Ptr[Result] = scala_app.binarybridge.impl.Implementations.scala_app_request(message, context)

  /**
   * [bindgen] header: /Users/velvetbaldmime/projects/Scala-Native-SwiftUI/Scala-Native-SwiftUI/headers/binary_interface.h
  */
  @exported
  override def scala_app_result_ok(result : Ptr[Result]): Boolean = scala_app.binarybridge.impl.Implementations.scala_app_result_ok(result)

object types:
  export _root_.scala_app.binarybridge.structs.*
  export _root_.scala_app.binarybridge.aliases.*

object all:
  export _root_.scala_app.binarybridge.aliases.Allocator
  export _root_.scala_app.binarybridge.aliases.size_t
  export _root_.scala_app.binarybridge.structs.ByteArray
  export _root_.scala_app.binarybridge.structs.Context
  export _root_.scala_app.binarybridge.structs.Result
