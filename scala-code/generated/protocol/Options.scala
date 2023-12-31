// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package protocol

@SerialVersionUID(0L)
final case class Options(
    debugLogging: _root_.scala.Boolean = false,
    printFullRequest: _root_.scala.Boolean = false,
    unknownFields: _root_.scalapb.UnknownFieldSet = _root_.scalapb.UnknownFieldSet.empty
    ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[Options] {
    @transient
    private[this] var __serializedSizeMemoized: _root_.scala.Int = 0
    private[this] def __computeSerializedSize(): _root_.scala.Int = {
      var __size = 0
      
      {
        val __value = debugLogging
        if (__value != false) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeBoolSize(1, __value)
        }
      };
      
      {
        val __value = printFullRequest
        if (__value != false) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeBoolSize(2, __value)
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
        val __v = debugLogging
        if (__v != false) {
          _output__.writeBool(1, __v)
        }
      };
      {
        val __v = printFullRequest
        if (__v != false) {
          _output__.writeBool(2, __v)
        }
      };
      unknownFields.writeTo(_output__)
    }
    def withDebugLogging(__v: _root_.scala.Boolean): Options = copy(debugLogging = __v)
    def withPrintFullRequest(__v: _root_.scala.Boolean): Options = copy(printFullRequest = __v)
    def withUnknownFields(__v: _root_.scalapb.UnknownFieldSet) = copy(unknownFields = __v)
    def discardUnknownFields = copy(unknownFields = _root_.scalapb.UnknownFieldSet.empty)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => {
          val __t = debugLogging
          if (__t != false) __t else null
        }
        case 2 => {
          val __t = printFullRequest
          if (__t != false) __t else null
        }
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => _root_.scalapb.descriptors.PBoolean(debugLogging)
        case 2 => _root_.scalapb.descriptors.PBoolean(printFullRequest)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
    def companion: protocol.Options.type = protocol.Options
    // @@protoc_insertion_point(GeneratedMessage[Options])
}

object Options extends scalapb.GeneratedMessageCompanion[protocol.Options] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[protocol.Options] = this
  def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): protocol.Options = {
    var __debugLogging: _root_.scala.Boolean = false
    var __printFullRequest: _root_.scala.Boolean = false
    var `_unknownFields__`: _root_.scalapb.UnknownFieldSet.Builder = null
    var _done__ = false
    while (!_done__) {
      val _tag__ = _input__.readTag()
      _tag__ match {
        case 0 => _done__ = true
        case 8 =>
          __debugLogging = _input__.readBool()
        case 16 =>
          __printFullRequest = _input__.readBool()
        case tag =>
          if (_unknownFields__ == null) {
            _unknownFields__ = new _root_.scalapb.UnknownFieldSet.Builder()
          }
          _unknownFields__.parseField(tag, _input__)
      }
    }
    protocol.Options(
        debugLogging = __debugLogging,
        printFullRequest = __printFullRequest,
        unknownFields = if (_unknownFields__ == null) _root_.scalapb.UnknownFieldSet.empty else _unknownFields__.result()
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[protocol.Options] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
      protocol.Options(
        debugLogging = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).map(_.as[_root_.scala.Boolean]).getOrElse(false),
        printFullRequest = __fieldsMap.get(scalaDescriptor.findFieldByNumber(2).get).map(_.as[_root_.scala.Boolean]).getOrElse(false)
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = ProtocolProto.javaDescriptor.getMessageTypes().get(9)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = ProtocolProto.scalaDescriptor.messages(9)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = throw new MatchError(__number)
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = protocol.Options(
    debugLogging = false,
    printFullRequest = false
  )
  implicit class OptionsLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, protocol.Options]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, protocol.Options](_l) {
    def debugLogging: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Boolean] = field(_.debugLogging)((c_, f_) => c_.copy(debugLogging = f_))
    def printFullRequest: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Boolean] = field(_.printFullRequest)((c_, f_) => c_.copy(printFullRequest = f_))
  }
  final val DEBUG_LOGGING_FIELD_NUMBER = 1
  final val PRINT_FULL_REQUEST_FIELD_NUMBER = 2
  def of(
    debugLogging: _root_.scala.Boolean,
    printFullRequest: _root_.scala.Boolean
  ): _root_.protocol.Options = _root_.protocol.Options(
    debugLogging,
    printFullRequest
  )
  // @@protoc_insertion_point(GeneratedMessageCompanion[Options])
}
