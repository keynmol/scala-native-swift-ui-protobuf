// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package protocol

@SerialVersionUID(0L)
final case class Response(
    payload: protocol.Response.Payload = protocol.Response.Payload.Empty,
    unknownFields: _root_.scalapb.UnknownFieldSet = _root_.scalapb.UnknownFieldSet.empty
    ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[Response] {
    @transient
    private[this] var __serializedSizeMemoized: _root_.scala.Int = 0
    private[this] def __computeSerializedSize(): _root_.scala.Int = {
      var __size = 0
      if (payload.login.isDefined) {
        val __value = payload.login.get
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      };
      if (payload.getWall.isDefined) {
        val __value = payload.getWall.get
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      };
      if (payload.getState.isDefined) {
        val __value = payload.getState.get
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      };
      if (payload.getMe.isDefined) {
        val __value = payload.getMe.get
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
      payload.login.foreach { __v =>
        val __m = __v
        _output__.writeTag(1, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      payload.getWall.foreach { __v =>
        val __m = __v
        _output__.writeTag(2, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      payload.getState.foreach { __v =>
        val __m = __v
        _output__.writeTag(3, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      payload.getMe.foreach { __v =>
        val __m = __v
        _output__.writeTag(4, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      unknownFields.writeTo(_output__)
    }
    def getLogin: protocol.Login.Response = payload.login.getOrElse(protocol.Login.Response.defaultInstance)
    def withLogin(__v: protocol.Login.Response): Response = copy(payload = protocol.Response.Payload.Login(__v))
    def getGetWall: protocol.GetWall.Response = payload.getWall.getOrElse(protocol.GetWall.Response.defaultInstance)
    def withGetWall(__v: protocol.GetWall.Response): Response = copy(payload = protocol.Response.Payload.GetWall(__v))
    def getGetState: protocol.GetState.Response = payload.getState.getOrElse(protocol.GetState.Response.defaultInstance)
    def withGetState(__v: protocol.GetState.Response): Response = copy(payload = protocol.Response.Payload.GetState(__v))
    def getGetMe: protocol.GetMe.Response = payload.getMe.getOrElse(protocol.GetMe.Response.defaultInstance)
    def withGetMe(__v: protocol.GetMe.Response): Response = copy(payload = protocol.Response.Payload.GetMe(__v))
    def clearPayload: Response = copy(payload = protocol.Response.Payload.Empty)
    def withPayload(__v: protocol.Response.Payload): Response = copy(payload = __v)
    def withUnknownFields(__v: _root_.scalapb.UnknownFieldSet) = copy(unknownFields = __v)
    def discardUnknownFields = copy(unknownFields = _root_.scalapb.UnknownFieldSet.empty)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => payload.login.orNull
        case 2 => payload.getWall.orNull
        case 3 => payload.getState.orNull
        case 4 => payload.getMe.orNull
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => payload.login.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
        case 2 => payload.getWall.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
        case 3 => payload.getState.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
        case 4 => payload.getMe.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
    def companion: protocol.Response.type = protocol.Response
    // @@protoc_insertion_point(GeneratedMessage[Response])
}

object Response extends scalapb.GeneratedMessageCompanion[protocol.Response] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[protocol.Response] = this
  def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): protocol.Response = {
    var __payload: protocol.Response.Payload = protocol.Response.Payload.Empty
    var `_unknownFields__`: _root_.scalapb.UnknownFieldSet.Builder = null
    var _done__ = false
    while (!_done__) {
      val _tag__ = _input__.readTag()
      _tag__ match {
        case 0 => _done__ = true
        case 10 =>
          __payload = protocol.Response.Payload.Login(__payload.login.fold(_root_.scalapb.LiteParser.readMessage[protocol.Login.Response](_input__))(_root_.scalapb.LiteParser.readMessage(_input__, _)))
        case 18 =>
          __payload = protocol.Response.Payload.GetWall(__payload.getWall.fold(_root_.scalapb.LiteParser.readMessage[protocol.GetWall.Response](_input__))(_root_.scalapb.LiteParser.readMessage(_input__, _)))
        case 26 =>
          __payload = protocol.Response.Payload.GetState(__payload.getState.fold(_root_.scalapb.LiteParser.readMessage[protocol.GetState.Response](_input__))(_root_.scalapb.LiteParser.readMessage(_input__, _)))
        case 34 =>
          __payload = protocol.Response.Payload.GetMe(__payload.getMe.fold(_root_.scalapb.LiteParser.readMessage[protocol.GetMe.Response](_input__))(_root_.scalapb.LiteParser.readMessage(_input__, _)))
        case tag =>
          if (_unknownFields__ == null) {
            _unknownFields__ = new _root_.scalapb.UnknownFieldSet.Builder()
          }
          _unknownFields__.parseField(tag, _input__)
      }
    }
    protocol.Response(
        payload = __payload,
        unknownFields = if (_unknownFields__ == null) _root_.scalapb.UnknownFieldSet.empty else _unknownFields__.result()
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[protocol.Response] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
      protocol.Response(
        payload = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).flatMap(_.as[_root_.scala.Option[protocol.Login.Response]]).map(protocol.Response.Payload.Login(_))
            .orElse[protocol.Response.Payload](__fieldsMap.get(scalaDescriptor.findFieldByNumber(2).get).flatMap(_.as[_root_.scala.Option[protocol.GetWall.Response]]).map(protocol.Response.Payload.GetWall(_)))
            .orElse[protocol.Response.Payload](__fieldsMap.get(scalaDescriptor.findFieldByNumber(3).get).flatMap(_.as[_root_.scala.Option[protocol.GetState.Response]]).map(protocol.Response.Payload.GetState(_)))
            .orElse[protocol.Response.Payload](__fieldsMap.get(scalaDescriptor.findFieldByNumber(4).get).flatMap(_.as[_root_.scala.Option[protocol.GetMe.Response]]).map(protocol.Response.Payload.GetMe(_)))
            .getOrElse(protocol.Response.Payload.Empty)
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = ProtocolProto.javaDescriptor.getMessageTypes().get(1)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = ProtocolProto.scalaDescriptor.messages(1)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = {
    var __out: _root_.scalapb.GeneratedMessageCompanion[_] = null
    (__number: @_root_.scala.unchecked) match {
      case 1 => __out = protocol.Login.Response
      case 2 => __out = protocol.GetWall.Response
      case 3 => __out = protocol.GetState.Response
      case 4 => __out = protocol.GetMe.Response
    }
    __out
  }
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = protocol.Response(
    payload = protocol.Response.Payload.Empty
  )
  sealed trait Payload extends _root_.scalapb.GeneratedOneof {
    def isEmpty: _root_.scala.Boolean = false
    def isDefined: _root_.scala.Boolean = true
    def isLogin: _root_.scala.Boolean = false
    def isGetWall: _root_.scala.Boolean = false
    def isGetState: _root_.scala.Boolean = false
    def isGetMe: _root_.scala.Boolean = false
    def login: _root_.scala.Option[protocol.Login.Response] = _root_.scala.None
    def getWall: _root_.scala.Option[protocol.GetWall.Response] = _root_.scala.None
    def getState: _root_.scala.Option[protocol.GetState.Response] = _root_.scala.None
    def getMe: _root_.scala.Option[protocol.GetMe.Response] = _root_.scala.None
  }
  object Payload {
    @SerialVersionUID(0L)
    case object Empty extends protocol.Response.Payload {
      type ValueType = _root_.scala.Nothing
      override def isEmpty: _root_.scala.Boolean = true
      override def isDefined: _root_.scala.Boolean = false
      override def number: _root_.scala.Int = 0
      override def value: _root_.scala.Nothing = throw new java.util.NoSuchElementException("Empty.value")
    }
  
    @SerialVersionUID(0L)
    final case class Login(value: protocol.Login.Response) extends protocol.Response.Payload {
      type ValueType = protocol.Login.Response
      override def isLogin: _root_.scala.Boolean = true
      override def login: _root_.scala.Option[protocol.Login.Response] = Some(value)
      override def number: _root_.scala.Int = 1
    }
    @SerialVersionUID(0L)
    final case class GetWall(value: protocol.GetWall.Response) extends protocol.Response.Payload {
      type ValueType = protocol.GetWall.Response
      override def isGetWall: _root_.scala.Boolean = true
      override def getWall: _root_.scala.Option[protocol.GetWall.Response] = Some(value)
      override def number: _root_.scala.Int = 2
    }
    @SerialVersionUID(0L)
    final case class GetState(value: protocol.GetState.Response) extends protocol.Response.Payload {
      type ValueType = protocol.GetState.Response
      override def isGetState: _root_.scala.Boolean = true
      override def getState: _root_.scala.Option[protocol.GetState.Response] = Some(value)
      override def number: _root_.scala.Int = 3
    }
    @SerialVersionUID(0L)
    final case class GetMe(value: protocol.GetMe.Response) extends protocol.Response.Payload {
      type ValueType = protocol.GetMe.Response
      override def isGetMe: _root_.scala.Boolean = true
      override def getMe: _root_.scala.Option[protocol.GetMe.Response] = Some(value)
      override def number: _root_.scala.Int = 4
    }
  }
  implicit class ResponseLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, protocol.Response]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, protocol.Response](_l) {
    def login: _root_.scalapb.lenses.Lens[UpperPB, protocol.Login.Response] = field(_.getLogin)((c_, f_) => c_.copy(payload = protocol.Response.Payload.Login(f_)))
    def getWall: _root_.scalapb.lenses.Lens[UpperPB, protocol.GetWall.Response] = field(_.getGetWall)((c_, f_) => c_.copy(payload = protocol.Response.Payload.GetWall(f_)))
    def getState: _root_.scalapb.lenses.Lens[UpperPB, protocol.GetState.Response] = field(_.getGetState)((c_, f_) => c_.copy(payload = protocol.Response.Payload.GetState(f_)))
    def getMe: _root_.scalapb.lenses.Lens[UpperPB, protocol.GetMe.Response] = field(_.getGetMe)((c_, f_) => c_.copy(payload = protocol.Response.Payload.GetMe(f_)))
    def payload: _root_.scalapb.lenses.Lens[UpperPB, protocol.Response.Payload] = field(_.payload)((c_, f_) => c_.copy(payload = f_))
  }
  final val LOGIN_FIELD_NUMBER = 1
  final val GET_WALL_FIELD_NUMBER = 2
  final val GET_STATE_FIELD_NUMBER = 3
  final val GET_ME_FIELD_NUMBER = 4
  def of(
    payload: protocol.Response.Payload
  ): _root_.protocol.Response = _root_.protocol.Response(
    payload
  )
  // @@protoc_insertion_point(GeneratedMessageCompanion[Response])
}