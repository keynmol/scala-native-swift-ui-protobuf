// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package protocol

@SerialVersionUID(0L)
final case class Request(
    payload: protocol.Request.Payload = protocol.Request.Payload.Empty,
    unknownFields: _root_.scalapb.UnknownFieldSet = _root_.scalapb.UnknownFieldSet.empty
    ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[Request] {
    @transient
    private[this] var __serializedSizeMemoized: _root_.scala.Int = 0
    private[this] def __computeSerializedSize(): _root_.scala.Int = {
      var __size = 0
      if (payload.addNumber.isDefined) {
        val __value = payload.addNumber.get
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      };
      if (payload.addString.isDefined) {
        val __value = payload.addString.get
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      };
      if (payload.getState.isDefined) {
        val __value = payload.getState.get
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
      payload.addNumber.foreach { __v =>
        val __m = __v
        _output__.writeTag(1, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      payload.addString.foreach { __v =>
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
      unknownFields.writeTo(_output__)
    }
    def getAddNumber: protocol.AddNumber.Request = payload.addNumber.getOrElse(protocol.AddNumber.Request.defaultInstance)
    def withAddNumber(__v: protocol.AddNumber.Request): Request = copy(payload = protocol.Request.Payload.AddNumber(__v))
    def getAddString: protocol.AddString.Request = payload.addString.getOrElse(protocol.AddString.Request.defaultInstance)
    def withAddString(__v: protocol.AddString.Request): Request = copy(payload = protocol.Request.Payload.AddString(__v))
    def getGetState: protocol.GetState.Request = payload.getState.getOrElse(protocol.GetState.Request.defaultInstance)
    def withGetState(__v: protocol.GetState.Request): Request = copy(payload = protocol.Request.Payload.GetState(__v))
    def clearPayload: Request = copy(payload = protocol.Request.Payload.Empty)
    def withPayload(__v: protocol.Request.Payload): Request = copy(payload = __v)
    def withUnknownFields(__v: _root_.scalapb.UnknownFieldSet) = copy(unknownFields = __v)
    def discardUnknownFields = copy(unknownFields = _root_.scalapb.UnknownFieldSet.empty)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => payload.addNumber.orNull
        case 2 => payload.addString.orNull
        case 3 => payload.getState.orNull
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => payload.addNumber.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
        case 2 => payload.addString.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
        case 3 => payload.getState.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
    def companion: protocol.Request.type = protocol.Request
    // @@protoc_insertion_point(GeneratedMessage[Request])
}

object Request extends scalapb.GeneratedMessageCompanion[protocol.Request] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[protocol.Request] = this
  def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): protocol.Request = {
    var __payload: protocol.Request.Payload = protocol.Request.Payload.Empty
    var `_unknownFields__`: _root_.scalapb.UnknownFieldSet.Builder = null
    var _done__ = false
    while (!_done__) {
      val _tag__ = _input__.readTag()
      _tag__ match {
        case 0 => _done__ = true
        case 10 =>
          __payload = protocol.Request.Payload.AddNumber(__payload.addNumber.fold(_root_.scalapb.LiteParser.readMessage[protocol.AddNumber.Request](_input__))(_root_.scalapb.LiteParser.readMessage(_input__, _)))
        case 18 =>
          __payload = protocol.Request.Payload.AddString(__payload.addString.fold(_root_.scalapb.LiteParser.readMessage[protocol.AddString.Request](_input__))(_root_.scalapb.LiteParser.readMessage(_input__, _)))
        case 26 =>
          __payload = protocol.Request.Payload.GetState(__payload.getState.fold(_root_.scalapb.LiteParser.readMessage[protocol.GetState.Request](_input__))(_root_.scalapb.LiteParser.readMessage(_input__, _)))
        case tag =>
          if (_unknownFields__ == null) {
            _unknownFields__ = new _root_.scalapb.UnknownFieldSet.Builder()
          }
          _unknownFields__.parseField(tag, _input__)
      }
    }
    protocol.Request(
        payload = __payload,
        unknownFields = if (_unknownFields__ == null) _root_.scalapb.UnknownFieldSet.empty else _unknownFields__.result()
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[protocol.Request] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
      protocol.Request(
        payload = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).flatMap(_.as[_root_.scala.Option[protocol.AddNumber.Request]]).map(protocol.Request.Payload.AddNumber(_))
            .orElse[protocol.Request.Payload](__fieldsMap.get(scalaDescriptor.findFieldByNumber(2).get).flatMap(_.as[_root_.scala.Option[protocol.AddString.Request]]).map(protocol.Request.Payload.AddString(_)))
            .orElse[protocol.Request.Payload](__fieldsMap.get(scalaDescriptor.findFieldByNumber(3).get).flatMap(_.as[_root_.scala.Option[protocol.GetState.Request]]).map(protocol.Request.Payload.GetState(_)))
            .getOrElse(protocol.Request.Payload.Empty)
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = ProtocolProto.javaDescriptor.getMessageTypes().get(0)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = ProtocolProto.scalaDescriptor.messages(0)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = {
    var __out: _root_.scalapb.GeneratedMessageCompanion[_] = null
    (__number: @_root_.scala.unchecked) match {
      case 1 => __out = protocol.AddNumber.Request
      case 2 => __out = protocol.AddString.Request
      case 3 => __out = protocol.GetState.Request
    }
    __out
  }
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = protocol.Request(
    payload = protocol.Request.Payload.Empty
  )
  sealed trait Payload extends _root_.scalapb.GeneratedOneof {
    def isEmpty: _root_.scala.Boolean = false
    def isDefined: _root_.scala.Boolean = true
    def isAddNumber: _root_.scala.Boolean = false
    def isAddString: _root_.scala.Boolean = false
    def isGetState: _root_.scala.Boolean = false
    def addNumber: _root_.scala.Option[protocol.AddNumber.Request] = _root_.scala.None
    def addString: _root_.scala.Option[protocol.AddString.Request] = _root_.scala.None
    def getState: _root_.scala.Option[protocol.GetState.Request] = _root_.scala.None
  }
  object Payload {
    @SerialVersionUID(0L)
    case object Empty extends protocol.Request.Payload {
      type ValueType = _root_.scala.Nothing
      override def isEmpty: _root_.scala.Boolean = true
      override def isDefined: _root_.scala.Boolean = false
      override def number: _root_.scala.Int = 0
      override def value: _root_.scala.Nothing = throw new java.util.NoSuchElementException("Empty.value")
    }
  
    @SerialVersionUID(0L)
    final case class AddNumber(value: protocol.AddNumber.Request) extends protocol.Request.Payload {
      type ValueType = protocol.AddNumber.Request
      override def isAddNumber: _root_.scala.Boolean = true
      override def addNumber: _root_.scala.Option[protocol.AddNumber.Request] = Some(value)
      override def number: _root_.scala.Int = 1
    }
    @SerialVersionUID(0L)
    final case class AddString(value: protocol.AddString.Request) extends protocol.Request.Payload {
      type ValueType = protocol.AddString.Request
      override def isAddString: _root_.scala.Boolean = true
      override def addString: _root_.scala.Option[protocol.AddString.Request] = Some(value)
      override def number: _root_.scala.Int = 2
    }
    @SerialVersionUID(0L)
    final case class GetState(value: protocol.GetState.Request) extends protocol.Request.Payload {
      type ValueType = protocol.GetState.Request
      override def isGetState: _root_.scala.Boolean = true
      override def getState: _root_.scala.Option[protocol.GetState.Request] = Some(value)
      override def number: _root_.scala.Int = 3
    }
  }
  implicit class RequestLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, protocol.Request]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, protocol.Request](_l) {
    def addNumber: _root_.scalapb.lenses.Lens[UpperPB, protocol.AddNumber.Request] = field(_.getAddNumber)((c_, f_) => c_.copy(payload = protocol.Request.Payload.AddNumber(f_)))
    def addString: _root_.scalapb.lenses.Lens[UpperPB, protocol.AddString.Request] = field(_.getAddString)((c_, f_) => c_.copy(payload = protocol.Request.Payload.AddString(f_)))
    def getState: _root_.scalapb.lenses.Lens[UpperPB, protocol.GetState.Request] = field(_.getGetState)((c_, f_) => c_.copy(payload = protocol.Request.Payload.GetState(f_)))
    def payload: _root_.scalapb.lenses.Lens[UpperPB, protocol.Request.Payload] = field(_.payload)((c_, f_) => c_.copy(payload = f_))
  }
  final val ADD_NUMBER_FIELD_NUMBER = 1
  final val ADD_STRING_FIELD_NUMBER = 2
  final val GET_STATE_FIELD_NUMBER = 3
  def of(
    payload: protocol.Request.Payload
  ): _root_.protocol.Request = _root_.protocol.Request(
    payload
  )
  // @@protoc_insertion_point(GeneratedMessageCompanion[Request])
}
