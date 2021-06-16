package world.cepi.orchestra.api.util

import java.io.DataInput
import java.io.DataInputStream
import java.io.IOException
import java.io.InputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder

/**
 * Add endian support to DataInputStream.
 * @author michael
 */
class EndianDataInputStream(stream: InputStream) : InputStream(), DataInput {
    var dataIn: DataInputStream = DataInputStream(stream)
    private val buffer = ByteBuffer.allocate(8)
    var order: ByteOrder = ByteOrder.LITTLE_ENDIAN
    fun order(o: ByteOrder): EndianDataInputStream {
        order = o
        return this
    }

    @Throws(IOException::class)
    override fun read(b: ByteArray): Int {
        return dataIn.read(b)
    }

    @Throws(IOException::class)
    override fun read(b: ByteArray, off: Int, len: Int): Int {
        return dataIn.read(b, off, len)
    }

    @Deprecated("This method does not properly convert bytes to characters.", ReplaceWith("dataIn.readUTF()"))
    @Throws(IOException::class)
    override fun readLine(): String {
        return dataIn.readLine()
    }

    @Throws(IOException::class)
    override fun readBoolean(): Boolean {
        return dataIn.readBoolean()
    }

    @Throws(IOException::class)
    override fun readByte(): Byte {
        return dataIn.readByte()
    }

    @Throws(IOException::class)
    override fun read(): Int {
        return readByte().toInt()
    }

    override fun markSupported(): Boolean {
        return dataIn.markSupported()
    }

    override fun mark(readlimit: Int) {
        dataIn.mark(readlimit)
    }

    @Throws(IOException::class)
    override fun reset() {
        dataIn.reset()
    }

    @Throws(IOException::class)
    override fun readChar(): Char {
        buffer.clear()
        buffer.order(ByteOrder.BIG_ENDIAN)
            .putLong(dataIn.readLong())
            .flip()
        return buffer.order(order).char
    }

    @Throws(IOException::class)
    override fun readFully(b: ByteArray) {
        dataIn.readFully(b)
    }

    @Throws(IOException::class)
    override fun readFully(b: ByteArray, off: Int, len: Int) {
        dataIn.readFully(b, off, len)
    }

    @Throws(IOException::class)
    override fun readUTF(): String {
        var length = readInt()

        if (length == 0) return ""

        val builder = StringBuilder(length)
        while (length > 0) {
            var c = readByte().toInt().toChar()
            if (c == 0x0D.toChar()) {
                c = ' '
            }
            builder.append(c)
            --length
        }
        return builder.toString()
    }

    @Throws(IOException::class)
    override fun skipBytes(n: Int): Int {
        return dataIn.skipBytes(n)
    }

    @Throws(IOException::class)
    override fun readDouble(): Double {
        val tmp = readLong()
        return java.lang.Double.longBitsToDouble(tmp)
    }

    @Throws(IOException::class)
    override fun readFloat(): Float {
        val tmp = readInt()
        return java.lang.Float.intBitsToFloat(tmp)
    }

    @Throws(IOException::class)
    override fun readInt(): Int {
        buffer.clear()
        buffer.order(ByteOrder.BIG_ENDIAN)
            .putInt(dataIn.readInt())
            .flip()
        return buffer.order(order).int
    }

    @Throws(IOException::class)
    override fun readLong(): Long {
        buffer.clear()
        buffer.order(ByteOrder.BIG_ENDIAN)
            .putLong(dataIn.readLong())
            .flip()
        return buffer.order(order).long
    }

    @Throws(IOException::class)
    override fun readShort(): Short {
        buffer.clear()
        buffer.order(ByteOrder.BIG_ENDIAN)
            .putShort(dataIn.readShort())
            .flip()
        return buffer.order(order).short
    }

    @Throws(IOException::class)
    override fun readUnsignedByte(): Int {
        return dataIn.readByte().toInt()
    }

    @Throws(IOException::class)
    override fun readUnsignedShort(): Int {
        return readShort().toInt()
    }

}