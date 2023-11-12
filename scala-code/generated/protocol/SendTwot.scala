// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package protocol

@SerialVersionUID(0L)
final case class SendTwot(
    unknownFields: _root_.scalapb.UnknownFieldSet = _root_.scalapb.UnknownFieldSet.empty
    ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[SendTwot] {
    @transient
    private[this] var __serializedSizeMemoized: _root_.scala.Int = 0
    private[this] def __computeSerializedSize(): _root_.scala.Int = {
      var __size = 0
      __size += unknownFields.serializedSize
      __size
    }
    override def serializedSize: _root_.scala.Int = {
      var __size = __serializedSizeMemoized
      if (__size == 0) {
        __size = __computeSerializedSize() + 1
        __serializedSizeMemoized = __size
      }
      __size - 1
      
    }
    def writeTo(`_output__`: _root_.com.google.protobuf.CodedOutputStream): _root_.scala.Unit = {
      unknownFields.writeTo(_output__)
    }
    def withUnknownFields(__v: _root_.scalapb.UnknownFieldSet) = copy(unknownFields = __v)
    def discardUnknownFields = copy(unknownFields = _root_.scalapb.UnknownFieldSet.empty)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = throw new MatchError(__fieldNumber)
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = throw new MatchError(__field)
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
    def companion: protocol.SendTwot.type = protocol.SendTwot
    // @@protoc_insertion_point(GeneratedMessage[SendTwot])
}

object SendTwot extends scalapb.GeneratedMessageCompanion[protocol.SendTwot] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[protocol.SendTwot] = this
  def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): protocol.SendTwot = {
    var `_unknownFields__`: _root_.scalapb.UnknownFieldSet.Builder = null
    var _done__ = false
    while (!_done__) {
      val _tag__ = _input__.readTag()
      _tag__ match {
        case 0 => _done__ = true
        case tag =>
          if (_unknownFields__ == null) {
            _unknownFields__ = new _root_.scalapb.UnknownFieldSet.Builder()
          }
          _unknownFields__.parseField(tag, _input__)
      }
    }
    protocol.SendTwot(
        unknownFields = if (_unknownFields__ == null) _root_.scalapb.UnknownFieldSet.empty else _unknownFields__.result()
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[protocol.SendTwot] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
      protocol.SendTwot(
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = ProtocolProto.javaDescriptor.getMessageTypes().get(2)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = ProtocolProto.scalaDescriptor.messages(2)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = throw new MatchError(__number)
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] =
    Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]](
      _root_.protocol.SendTwot.Request,
      _root_.protocol.SendTwot.Response
    )
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = protocol.SendTwot(
  )
  @SerialVersionUID(0L)
  final case class Request(
      text: _root_.scala.Predef.String = "",
      token: _root_.scala.Predef.String = "",
      unknownFields: _root_.scalapb.UnknownFieldSet = _root_.scalapb.UnknownFieldSet.empty
      ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[Request] {
      @transient
      private[this] var __serializedSizeMemoized: _root_.scala.Int = 0
      private[this] def __computeSerializedSize(): _root_.scala.Int = {
        var __size = 0
        
        {
          val __value = text
          if (!__value.isEmpty) {
            __size += _root_.com.google.protobuf.CodedOutputStream.computeStringSize(1, __value)
          }
        };
        
        {
          val __value = token
          if (!__value.isEmpty) {
            __size += _root_.com.google.protobuf.CodedOutputStream.computeStringSize(2, __value)
          }
        };
        __size += unknownFields.serializedSize
        __size
      }
      override def serializedSize: _root_.scala.Int = {
        var __size = __serializedSizeMemoized
        if (__size == 0) {
          __size = __computeSerializedSize() + 1
          __serializedSizeMemoized = __size
        }
        __size - 1
        
      }
      def writeTo(`_output__`: _root_.com.google.protobuf.CodedOutputStream): _root_.scala.Unit = {
        {
          val __v = text
          if (!__v.isEmpty) {
            _output__.writeString(1, __v)
          }
        };
        {
          val __v = token
          if (!__v.isEmpty) {
            _output__.writeString(2, __v)
          }
        };
        unknownFields.writeTo(_output__)
      }
      def withText(__v: _root_.scala.Predef.String): Request = copy(text = __v)
      def withToken(__v: _root_.scala.Predef.String): Request = copy(token = __v)
      def withUnknownFields(__v: _root_.scalapb.UnknownFieldSet) = copy(unknownFields = __v)
      def discardUnknownFields = copy(unknownFields = _root_.scalapb.UnknownFieldSet.empty)
      def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
        (__fieldNumber: @_root_.scala.unchecked) match {
          case 1 => {
            val __t = text
            if (__t != "") __t else null
          }
          case 2 => {
            val __t = token
            if (__t != "") __t else null
          }
        }
      }
      def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
        _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
        (__field.number: @_root_.scala.unchecked) match {
          case 1 => _root_.scalapb.descriptors.PString(text)
          case 2 => _root_.scalapb.descriptors.PString(token)
        }
      }
      def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
      def companion: protocol.SendTwot.Request.type = protocol.SendTwot.Request
      // @@protoc_insertion_point(GeneratedMessage[SendTwot.Request])
  }
  
  object Request extends scalapb.GeneratedMessageCompanion[protocol.SendTwot.Request] {
    implicit def messageCompanion: scalapb.GeneratedMessageCompanion[protocol.SendTwot.Request] = this
    def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): protocol.SendTwot.Request = {
      var __text: _root_.scala.Predef.String = ""
      var __token: _root_.scala.Predef.String = ""
      var `_unknownFields__`: _root_.scalapb.UnknownFieldSet.Builder = null
      var _done__ = false
      while (!_done__) {
        val _tag__ = _input__.readTag()
        _tag__ match {
          case 0 => _done__ = true
          case 10 =>
            __text = _input__.readStringRequireUtf8()
          case 18 =>
            __token = _input__.readStringRequireUtf8()
          case tag =>
            if (_unknownFields__ == null) {
              _unknownFields__ = new _root_.scalapb.UnknownFieldSet.Builder()
            }
            _unknownFields__.parseField(tag, _input__)
        }
      }
      protocol.SendTwot.Request(
          text = __text,
          token = __token,
          unknownFields = if (_unknownFields__ == null) _root_.scalapb.UnknownFieldSet.empty else _unknownFields__.result()
      )
    }
    implicit def messageReads: _root_.scalapb.descriptors.Reads[protocol.SendTwot.Request] = _root_.scalapb.descriptors.Reads{
      case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
        _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
        protocol.SendTwot.Request(
          text = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).map(_.as[_root_.scala.Predef.String]).getOrElse(""),
          token = __fieldsMap.get(scalaDescriptor.findFieldByNumber(2).get).map(_.as[_root_.scala.Predef.String]).getOrElse("")
        )
      case _ => throw new RuntimeException("Expected PMessage")
    }
    def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = protocol.SendTwot.javaDescriptor.getNestedTypes().get(0)
    def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = protocol.SendTwot.scalaDescriptor.nestedMessages(0)
    def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = throw new MatchError(__number)
    lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
    def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
    lazy val defaultInstance = protocol.SendTwot.Request(
      text = "",
      token = ""
    )
    implicit class RequestLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, protocol.SendTwot.Request]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, protocol.SendTwot.Request](_l) {
      def text: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Predef.String] = field(_.text)((c_, f_) => c_.copy(text = f_))
      def token: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Predef.String] = field(_.token)((c_, f_) => c_.copy(token = f_))
    }
    final val TEXT_FIELD_NUMBER = 1
    final val TOKEN_FIELD_NUMBER = 2
    def of(
      text: _root_.scala.Predef.String,
      token: _root_.scala.Predef.String
    ): _root_.protocol.SendTwot.Request = _root_.protocol.SendTwot.Request(
      text,
      token
    )
    // @@protoc_insertion_point(GeneratedMessageCompanion[SendTwot.Request])
  }
  
  @SerialVersionUID(0L)
  final case class Response(
      unknownFields: _root_.scalapb.UnknownFieldSet = _root_.scalapb.UnknownFieldSet.empty
      ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[Response] {
      @transient
      private[this] var __serializedSizeMemoized: _root_.scala.Int = 0
      private[this] def __computeSerializedSize(): _root_.scala.Int = {
        var __size = 0
        __size += unknownFields.serializedSize
        __size
      }
      override def serializedSize: _root_.scala.Int = {
        var __size = __serializedSizeMemoized
        if (__size == 0) {
          __size = __computeSerializedSize() + 1
          __serializedSizeMemoized = __size
        }
        __size - 1
        
      }
      def writeTo(`_output__`: _root_.com.google.protobuf.CodedOutputStream): _root_.scala.Unit = {
        unknownFields.writeTo(_output__)
      }
      def withUnknownFields(__v: _root_.scalapb.UnknownFieldSet) = copy(unknownFields = __v)
      def discardUnknownFields = copy(unknownFields = _root_.scalapb.UnknownFieldSet.empty)
      def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = throw new MatchError(__fieldNumber)
      def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = throw new MatchError(__field)
      def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
      def companion: protocol.SendTwot.Response.type = protocol.SendTwot.Response
      // @@protoc_insertion_point(GeneratedMessage[SendTwot.Response])
  }
  
  object Response extends scalapb.GeneratedMessageCompanion[protocol.SendTwot.Response] {
    implicit def messageCompanion: scalapb.GeneratedMessageCompanion[protocol.SendTwot.Response] = this
    def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): protocol.SendTwot.Response = {
      var `_unknownFields__`: _root_.scalapb.UnknownFieldSet.Builder = null
      var _done__ = false
      while (!_done__) {
        val _tag__ = _input__.readTag()
        _tag__ match {
          case 0 => _done__ = true
          case tag =>
            if (_unknownFields__ == null) {
              _unknownFields__ = new _root_.scalapb.UnknownFieldSet.Builder()
            }
            _unknownFields__.parseField(tag, _input__)
        }
      }
      protocol.SendTwot.Response(
          unknownFields = if (_unknownFields__ == null) _root_.scalapb.UnknownFieldSet.empty else _unknownFields__.result()
      )
    }
    implicit def messageReads: _root_.scalapb.descriptors.Reads[protocol.SendTwot.Response] = _root_.scalapb.descriptors.Reads{
      case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
        _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
        protocol.SendTwot.Response(
        )
      case _ => throw new RuntimeException("Expected PMessage")
    }
    def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = protocol.SendTwot.javaDescriptor.getNestedTypes().get(1)
    def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = protocol.SendTwot.scalaDescriptor.nestedMessages(1)
    def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = throw new MatchError(__number)
    lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
    def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
    lazy val defaultInstance = protocol.SendTwot.Response(
    )
    implicit class ResponseLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, protocol.SendTwot.Response]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, protocol.SendTwot.Response](_l) {
    }
    def of(
    ): _root_.protocol.SendTwot.Response = _root_.protocol.SendTwot.Response(
    )
    // @@protoc_insertion_point(GeneratedMessageCompanion[SendTwot.Response])
  }
  
  implicit class SendTwotLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, protocol.SendTwot]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, protocol.SendTwot](_l) {
  }
  def of(
  ): _root_.protocol.SendTwot = _root_.protocol.SendTwot(
  )
  // @@protoc_insertion_point(GeneratedMessageCompanion[SendTwot])
}