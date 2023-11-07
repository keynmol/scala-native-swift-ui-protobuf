// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package protocol

@SerialVersionUID(0L)
final case class State(
    sum: _root_.scala.Int = 0,
    result: _root_.scala.Predef.String = "",
    unknownFields: _root_.scalapb.UnknownFieldSet = _root_.scalapb.UnknownFieldSet.empty
    ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[State] {
    @transient
    private[this] var __serializedSizeMemoized: _root_.scala.Int = 0
    private[this] def __computeSerializedSize(): _root_.scala.Int = {
      var __size = 0
      
      {
        val __value = sum
        if (__value != 0) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeInt32Size(1, __value)
        }
      };
      
      {
        val __value = result
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
        val __v = sum
        if (__v != 0) {
          _output__.writeInt32(1, __v)
        }
      };
      {
        val __v = result
        if (!__v.isEmpty) {
          _output__.writeString(2, __v)
        }
      };
      unknownFields.writeTo(_output__)
    }
    def withSum(__v: _root_.scala.Int): State = copy(sum = __v)
    def withResult(__v: _root_.scala.Predef.String): State = copy(result = __v)
    def withUnknownFields(__v: _root_.scalapb.UnknownFieldSet) = copy(unknownFields = __v)
    def discardUnknownFields = copy(unknownFields = _root_.scalapb.UnknownFieldSet.empty)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => {
          val __t = sum
          if (__t != 0) __t else null
        }
        case 2 => {
          val __t = result
          if (__t != "") __t else null
        }
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => _root_.scalapb.descriptors.PInt(sum)
        case 2 => _root_.scalapb.descriptors.PString(result)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
    def companion: protocol.State.type = protocol.State
    // @@protoc_insertion_point(GeneratedMessage[State])
}

object State extends scalapb.GeneratedMessageCompanion[protocol.State] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[protocol.State] = this
  def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): protocol.State = {
    var __sum: _root_.scala.Int = 0
    var __result: _root_.scala.Predef.String = ""
    var `_unknownFields__`: _root_.scalapb.UnknownFieldSet.Builder = null
    var _done__ = false
    while (!_done__) {
      val _tag__ = _input__.readTag()
      _tag__ match {
        case 0 => _done__ = true
        case 8 =>
          __sum = _input__.readInt32()
        case 18 =>
          __result = _input__.readStringRequireUtf8()
        case tag =>
          if (_unknownFields__ == null) {
            _unknownFields__ = new _root_.scalapb.UnknownFieldSet.Builder()
          }
          _unknownFields__.parseField(tag, _input__)
      }
    }
    protocol.State(
        sum = __sum,
        result = __result,
        unknownFields = if (_unknownFields__ == null) _root_.scalapb.UnknownFieldSet.empty else _unknownFields__.result()
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[protocol.State] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
      protocol.State(
        sum = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).map(_.as[_root_.scala.Int]).getOrElse(0),
        result = __fieldsMap.get(scalaDescriptor.findFieldByNumber(2).get).map(_.as[_root_.scala.Predef.String]).getOrElse("")
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = ProtocolProto.javaDescriptor.getMessageTypes().get(6)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = ProtocolProto.scalaDescriptor.messages(6)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = throw new MatchError(__number)
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = protocol.State(
    sum = 0,
    result = ""
  )
  implicit class StateLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, protocol.State]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, protocol.State](_l) {
    def sum: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Int] = field(_.sum)((c_, f_) => c_.copy(sum = f_))
    def result: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Predef.String] = field(_.result)((c_, f_) => c_.copy(result = f_))
  }
  final val SUM_FIELD_NUMBER = 1
  final val RESULT_FIELD_NUMBER = 2
  def of(
    sum: _root_.scala.Int,
    result: _root_.scala.Predef.String
  ): _root_.protocol.State = _root_.protocol.State(
    sum,
    result
  )
  // @@protoc_insertion_point(GeneratedMessageCompanion[State])
}