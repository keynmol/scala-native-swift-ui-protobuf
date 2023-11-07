// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package protocol

object ProtocolProto extends _root_.scalapb.GeneratedFileObject {
  lazy val dependencies: Seq[_root_.scalapb.GeneratedFileObject] = Seq.empty
  lazy val messagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] =
    Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]](
      protocol.Request,
      protocol.Response,
      protocol.GetState,
      protocol.AddNumber,
      protocol.AddString,
      protocol.Error,
      protocol.State,
      protocol.Options
    )
  private lazy val ProtoBytes: _root_.scala.Array[Byte] =
      scalapb.Encoding.fromBase64(scala.collection.immutable.Seq(
  """Cg5wcm90b2NvbC5wcm90byLfAQoHUmVxdWVzdBJDCgphZGRfbnVtYmVyGAEgASgLMhIuQWRkTnVtYmVyLlJlcXVlc3RCDuI/C
  xIJYWRkTnVtYmVySABSCWFkZE51bWJlchJDCgphZGRfc3RyaW5nGAIgASgLMhIuQWRkU3RyaW5nLlJlcXVlc3RCDuI/CxIJYWRkU
  3RyaW5nSABSCWFkZFN0cmluZxI/CglnZXRfc3RhdGUYAyABKAsyES5HZXRTdGF0ZS5SZXF1ZXN0Qg3iPwoSCGdldFN0YXRlSABSC
  GdldFN0YXRlQgkKB3BheWxvYWQimwIKCFJlc3BvbnNlEkQKCmFkZF9udW1iZXIYASABKAsyEy5BZGROdW1iZXIuUmVzcG9uc2VCD
  uI/CxIJYWRkTnVtYmVySABSCWFkZE51bWJlchJECgphZGRfc3RyaW5nGAIgASgLMhMuQWRkU3RyaW5nLlJlc3BvbnNlQg7iPwsSC
  WFkZFN0cmluZ0gAUglhZGRTdHJpbmcSQAoJZ2V0X3N0YXRlGAQgASgLMhIuR2V0U3RhdGUuUmVzcG9uc2VCDeI/ChIIZ2V0U3Rhd
  GVIAFIIZ2V0U3RhdGUSNgoNc3RhdGVfY2hhbmdlZBgDIAEoCEIR4j8OEgxzdGF0ZUNoYW5nZWRSDHN0YXRlQ2hhbmdlZEIJCgdwY
  Xlsb2FkIksKCEdldFN0YXRlGgkKB1JlcXVlc3QaNAoIUmVzcG9uc2USKAoFc3RhdGUYASABKAsyBi5TdGF0ZUIK4j8HEgVzdGF0Z
  VIFc3RhdGUiRwoJQWRkTnVtYmVyGi4KB1JlcXVlc3QSIwoGYW1vdW50GAEgASgFQgviPwgSBmFtb3VudFIGYW1vdW50GgoKCFJlc
  3BvbnNlIkQKCUFkZFN0cmluZxorCgdSZXF1ZXN0EiAKBXZhbHVlGAEgASgJQgriPwcSBXZhbHVlUgV2YWx1ZRoKCghSZXNwb25zZ
  SIvCgVFcnJvchImCgdtZXNzYWdlGAEgASgJQgziPwkSB21lc3NhZ2VSB21lc3NhZ2UiSAoFU3RhdGUSGgoDc3VtGAEgASgFQgjiP
  wUSA3N1bVIDc3VtEiMKBnJlc3VsdBgCIAEoCUIL4j8IEgZyZXN1bHRSBnJlc3VsdCJBCgdPcHRpb25zEjYKDWRlYnVnX2xvZ2dpb
  mcYASABKAhCEeI/DhIMZGVidWdMb2dnaW5nUgxkZWJ1Z0xvZ2dpbmdiBnByb3RvMw=="""
      ).mkString)
  lazy val scalaDescriptor: _root_.scalapb.descriptors.FileDescriptor = {
    val scalaProto = com.google.protobuf.descriptor.FileDescriptorProto.parseFrom(ProtoBytes)
    _root_.scalapb.descriptors.FileDescriptor.buildFrom(scalaProto, dependencies.map(_.scalaDescriptor))
  }
  lazy val javaDescriptor: com.google.protobuf.Descriptors.FileDescriptor = {
    val javaProto = com.google.protobuf.DescriptorProtos.FileDescriptorProto.parseFrom(ProtoBytes)
    com.google.protobuf.Descriptors.FileDescriptor.buildFrom(javaProto, _root_.scala.Array(
    ))
  }
  @deprecated("Use javaDescriptor instead. In a future version this will refer to scalaDescriptor.", "ScalaPB 0.5.47")
  def descriptor: com.google.protobuf.Descriptors.FileDescriptor = javaDescriptor
}