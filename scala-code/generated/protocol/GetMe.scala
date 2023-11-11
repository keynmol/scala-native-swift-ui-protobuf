// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package protocol

@SerialVersionUID(0L)
final case class GetMe(
    unknownFields: _root_.scalapb.UnknownFieldSet = _root_.scalapb.UnknownFieldSet.empty
    ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[GetMe] {
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
    def companion: protocol.GetMe.type = protocol.GetMe
    // @@protoc_insertion_point(GeneratedMessage[GetMe])
}

object GetMe extends scalapb.GeneratedMessageCompanion[protocol.GetMe] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[protocol.GetMe] = this
  def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): protocol.GetMe = {
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
    protocol.GetMe(
        unknownFields = if (_unknownFields__ == null) _root_.scalapb.UnknownFieldSet.empty else _unknownFields__.result()
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[protocol.GetMe] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
      protocol.GetMe(
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = ProtocolProto.javaDescriptor.getMessageTypes().get(2)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = ProtocolProto.scalaDescriptor.messages(2)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = throw new MatchError(__number)
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] =
    Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]](
      _root_.protocol.GetMe.Request,
      _root_.protocol.GetMe.Response
    )
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = protocol.GetMe(
  )
  @SerialVersionUID(0L)
  final case class Request(
      token: _root_.scala.Predef.String = "",
      unknownFields: _root_.scalapb.UnknownFieldSet = _root_.scalapb.UnknownFieldSet.empty
      ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[Request] {
      @transient
      private[this] var __serializedSizeMemoized: _root_.scala.Int = 0
      private[this] def __computeSerializedSize(): _root_.scala.Int = {
        var __size = 0
        
        {
          val __value = token
          if (!__value.isEmpty) {
            __size += _root_.com.google.protobuf.CodedOutputStream.computeStringSize(1, __value)
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
          val __v = token
          if (!__v.isEmpty) {
            _output__.writeString(1, __v)
          }
        };
        unknownFields.writeTo(_output__)
      }
      def withToken(__v: _root_.scala.Predef.String): Request = copy(token = __v)
      def withUnknownFields(__v: _root_.scalapb.UnknownFieldSet) = copy(unknownFields = __v)
      def discardUnknownFields = copy(unknownFields = _root_.scalapb.UnknownFieldSet.empty)
      def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
        (__fieldNumber: @_root_.scala.unchecked) match {
          case 1 => {
            val __t = token
            if (__t != "") __t else null
          }
        }
      }
      def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
        _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
        (__field.number: @_root_.scala.unchecked) match {
          case 1 => _root_.scalapb.descriptors.PString(token)
        }
      }
      def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
      def companion: protocol.GetMe.Request.type = protocol.GetMe.Request
      // @@protoc_insertion_point(GeneratedMessage[GetMe.Request])
  }
  
  object Request extends scalapb.GeneratedMessageCompanion[protocol.GetMe.Request] {
    implicit def messageCompanion: scalapb.GeneratedMessageCompanion[protocol.GetMe.Request] = this
    def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): protocol.GetMe.Request = {
      var __token: _root_.scala.Predef.String = ""
      var `_unknownFields__`: _root_.scalapb.UnknownFieldSet.Builder = null
      var _done__ = false
      while (!_done__) {
        val _tag__ = _input__.readTag()
        _tag__ match {
          case 0 => _done__ = true
          case 10 =>
            __token = _input__.readStringRequireUtf8()
          case tag =>
            if (_unknownFields__ == null) {
              _unknownFields__ = new _root_.scalapb.UnknownFieldSet.Builder()
            }
            _unknownFields__.parseField(tag, _input__)
        }
      }
      protocol.GetMe.Request(
          token = __token,
          unknownFields = if (_unknownFields__ == null) _root_.scalapb.UnknownFieldSet.empty else _unknownFields__.result()
      )
    }
    implicit def messageReads: _root_.scalapb.descriptors.Reads[protocol.GetMe.Request] = _root_.scalapb.descriptors.Reads{
      case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
        _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
        protocol.GetMe.Request(
          token = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).map(_.as[_root_.scala.Predef.String]).getOrElse("")
        )
      case _ => throw new RuntimeException("Expected PMessage")
    }
    def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = protocol.GetMe.javaDescriptor.getNestedTypes().get(0)
    def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = protocol.GetMe.scalaDescriptor.nestedMessages(0)
    def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = throw new MatchError(__number)
    lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
    def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
    lazy val defaultInstance = protocol.GetMe.Request(
      token = ""
    )
    implicit class RequestLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, protocol.GetMe.Request]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, protocol.GetMe.Request](_l) {
      def token: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Predef.String] = field(_.token)((c_, f_) => c_.copy(token = f_))
    }
    final val TOKEN_FIELD_NUMBER = 1
    def of(
      token: _root_.scala.Predef.String
    ): _root_.protocol.GetMe.Request = _root_.protocol.GetMe.Request(
      token
    )
    // @@protoc_insertion_point(GeneratedMessageCompanion[GetMe.Request])
  }
  
  @SerialVersionUID(0L)
  final case class Response(
      me: _root_.scala.Option[protocol.Me] = _root_.scala.None,
      unknownFields: _root_.scalapb.UnknownFieldSet = _root_.scalapb.UnknownFieldSet.empty
      ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[Response] {
      @transient
      private[this] var __serializedSizeMemoized: _root_.scala.Int = 0
      private[this] def __computeSerializedSize(): _root_.scala.Int = {
        var __size = 0
        if (me.isDefined) {
          val __value = me.get
          __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
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
        me.foreach { __v =>
          val __m = __v
          _output__.writeTag(1, 2)
          _output__.writeUInt32NoTag(__m.serializedSize)
          __m.writeTo(_output__)
        };
        unknownFields.writeTo(_output__)
      }
      def getMe: protocol.Me = me.getOrElse(protocol.Me.defaultInstance)
      def clearMe: Response = copy(me = _root_.scala.None)
      def withMe(__v: protocol.Me): Response = copy(me = Option(__v))
      def withUnknownFields(__v: _root_.scalapb.UnknownFieldSet) = copy(unknownFields = __v)
      def discardUnknownFields = copy(unknownFields = _root_.scalapb.UnknownFieldSet.empty)
      def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
        (__fieldNumber: @_root_.scala.unchecked) match {
          case 1 => me.orNull
        }
      }
      def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
        _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
        (__field.number: @_root_.scala.unchecked) match {
          case 1 => me.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
        }
      }
      def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
      def companion: protocol.GetMe.Response.type = protocol.GetMe.Response
      // @@protoc_insertion_point(GeneratedMessage[GetMe.Response])
  }
  
  object Response extends scalapb.GeneratedMessageCompanion[protocol.GetMe.Response] {
    implicit def messageCompanion: scalapb.GeneratedMessageCompanion[protocol.GetMe.Response] = this
    def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): protocol.GetMe.Response = {
      var __me: _root_.scala.Option[protocol.Me] = _root_.scala.None
      var `_unknownFields__`: _root_.scalapb.UnknownFieldSet.Builder = null
      var _done__ = false
      while (!_done__) {
        val _tag__ = _input__.readTag()
        _tag__ match {
          case 0 => _done__ = true
          case 10 =>
            __me = Option(__me.fold(_root_.scalapb.LiteParser.readMessage[protocol.Me](_input__))(_root_.scalapb.LiteParser.readMessage(_input__, _)))
          case tag =>
            if (_unknownFields__ == null) {
              _unknownFields__ = new _root_.scalapb.UnknownFieldSet.Builder()
            }
            _unknownFields__.parseField(tag, _input__)
        }
      }
      protocol.GetMe.Response(
          me = __me,
          unknownFields = if (_unknownFields__ == null) _root_.scalapb.UnknownFieldSet.empty else _unknownFields__.result()
      )
    }
    implicit def messageReads: _root_.scalapb.descriptors.Reads[protocol.GetMe.Response] = _root_.scalapb.descriptors.Reads{
      case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
        _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
        protocol.GetMe.Response(
          me = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).flatMap(_.as[_root_.scala.Option[protocol.Me]])
        )
      case _ => throw new RuntimeException("Expected PMessage")
    }
    def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = protocol.GetMe.javaDescriptor.getNestedTypes().get(1)
    def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = protocol.GetMe.scalaDescriptor.nestedMessages(1)
    def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = {
      var __out: _root_.scalapb.GeneratedMessageCompanion[_] = null
      (__number: @_root_.scala.unchecked) match {
        case 1 => __out = protocol.Me
      }
      __out
    }
    lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
    def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
    lazy val defaultInstance = protocol.GetMe.Response(
      me = _root_.scala.None
    )
    implicit class ResponseLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, protocol.GetMe.Response]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, protocol.GetMe.Response](_l) {
      def me: _root_.scalapb.lenses.Lens[UpperPB, protocol.Me] = field(_.getMe)((c_, f_) => c_.copy(me = Option(f_)))
      def optionalMe: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Option[protocol.Me]] = field(_.me)((c_, f_) => c_.copy(me = f_))
    }
    final val ME_FIELD_NUMBER = 1
    def of(
      me: _root_.scala.Option[protocol.Me]
    ): _root_.protocol.GetMe.Response = _root_.protocol.GetMe.Response(
      me
    )
    // @@protoc_insertion_point(GeneratedMessageCompanion[GetMe.Response])
  }
  
  implicit class GetMeLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, protocol.GetMe]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, protocol.GetMe](_l) {
  }
  def of(
  ): _root_.protocol.GetMe = _root_.protocol.GetMe(
  )
  // @@protoc_insertion_point(GeneratedMessageCompanion[GetMe])
}
