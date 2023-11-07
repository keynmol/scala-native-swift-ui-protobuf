package scala_app

import scala.collection.mutable.ListBuffer
import scala.scalanative.unsafe.*
import scala.scalanative.runtime.libc
import scala.scalanative.runtime.Intrinsics

class DangerZone private (
    private val ar: ListBuffer[Ptr[Byte]],
    private var sz: Long
) extends Zone:
  private var closed = false
  override def alloc(size: CSize): Ptr[Byte] =
    if closed then null
    else
      val rawPtr = libc.malloc(size)
      assert(
        rawPtr != null,
        s"Catastrophic: failed to allocate ${size} bytes using system allocator"
      )
      sz += size.toLong
      val ptr = scalanative.runtime.fromRawPtr[Byte](rawPtr)
      ar.addOne(ptr)
      ptr

  override def close(): Unit = closed = true
  override def isClosed: Boolean = closed
end DangerZone

object DangerZone:
  // Location of the Zone from the internal map
  opaque type Token = Int
  object Token:
    given Tag[Token] = Tag.Int

    inline def fromInt(i: Int): Token = i

    inline def fromPtr(ptr: Ptr[Byte]): Token =
      Intrinsics.castRawPtrToInt(scalanative.runtime.toRawPtr(ptr))

    extension (inline t: Token)
      inline def toPtr: Ptr[Byte] =
        scalanative.runtime.fromRawPtr[Byte](Intrinsics.castIntToRawPtr(t))

      inline def toInt: Int = t
  end Token

  private var curIdx = 0
  private val chains = collection.mutable.Map.empty[Int, DangerZone]

  def use[A](tok: Ptr[Token])(f: DangerZone ?=> A): A =
    val (buffer, token) = chains.synchronized:
      val newBuf = DangerZone(ListBuffer.empty[Ptr[Byte]], 0L)
      if chains.isEmpty then curIdx = 0
      chains.update(curIdx, newBuf)
      curIdx += 1
      (newBuf, curIdx - 1)

    !tok = token

    f(using buffer)
  end use

  def useWithToken[A](f: Token => DangerZone ?=> A): A =
    val (buffer, token) = chains.synchronized:
      val newBuf = DangerZone(ListBuffer.empty[Ptr[Byte]], 0L)
      if chains.isEmpty then curIdx = 0
      chains.update(curIdx, newBuf)
      curIdx += 1
      (newBuf, curIdx - 1)

    f(token)(using buffer)
  end useWithToken

  def free(tok: Token): Boolean =
    chains.synchronized:
      if chains.contains(tok) then
        val chain = chains(tok)
        chain.ar.reverse.foreach(ptr =>
          libc.free(scalanative.runtime.toRawPtr(ptr))
        )

        scribe.debug(
          s"Deallocating id ${tok}, contains ${chain.ar.length} pointers (total size: ${chain.sz} bytes)"
        )
        chains.remove(tok)
        chain.close()
        true
      else false
end DangerZone
