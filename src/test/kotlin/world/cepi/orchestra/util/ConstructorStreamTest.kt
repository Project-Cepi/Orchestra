package world.cepi.orchestra.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.PipedInputStream
import java.io.PipedOutputStream

class ConstructorStreamTest {

    data class ValidClass(
        val byte: Byte,
        val short: Short,
        val int: Int,
        val long: Long,
        val double: Double,
        val float: Float,
        val char: Char,
        val boolean: Boolean,
        val string: String
    )

    @Test
    fun `make sure valid classes parse correctly`() {

        val pipeOutput = PipedOutputStream()

        val pipeInput = PipedInputStream()

        pipeOutput.connect(pipeInput)

        val dataOutput = DataOutputStream(pipeOutput)

        dataOutput.writeByte(Byte.MAX_VALUE.toInt())
        dataOutput.writeShort(Short.MAX_VALUE.toInt())
        dataOutput.writeInt(Int.MAX_VALUE)
        dataOutput.writeLong(Long.MAX_VALUE)
        dataOutput.writeDouble(Double.MAX_VALUE)
        dataOutput.writeFloat(Float.MAX_VALUE)
        dataOutput.writeChar('a'.code)
        dataOutput.writeBoolean(true)
        dataOutput.writeUTF("Hello")

        val instance = ConstructorStreamParser.createInstance<ValidClass>(DataInputStream(pipeInput))

        assertNotEquals(null, instance)

        assertEquals(ValidClass(
            Byte.MAX_VALUE,
            Short.MAX_VALUE,
            Int.MAX_VALUE,
            Long.MAX_VALUE,
            Double.MAX_VALUE,
            Float.MAX_VALUE,
            'a',
            true,
            "Hello"
        ), instance)

    }

}