package world.cepi.orchestra.api.util

import java.io.DataInput
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.full.valueParameters

object ConstructorStreamParser {

    fun <T : Any> createInstance(dataInputStream: DataInput, clazz: KClass<T>): T? {
        val constructor = clazz.primaryConstructor ?: return null

        return constructor.call(*constructor.valueParameters.map {

            when (it.type.classifier as? KClass<*> ?: return null) {
                Byte::class -> dataInputStream.readByte()
                UByte::class -> (dataInputStream.readByte() + (-Byte.MIN_VALUE)).toUByte()
                Short::class -> dataInputStream.readShort()
                UShort::class -> (dataInputStream.readShort() + (-Short.MAX_VALUE)).toUShort()
                Int::class -> dataInputStream.readInt()
                UInt::class -> (dataInputStream.readInt() + (-Int.MAX_VALUE)).toUInt()
                Long::class -> dataInputStream.readLong()
                ULong::class -> (dataInputStream.readLong() + (-Long.MAX_VALUE)).toULong()
                Double::class -> dataInputStream.readDouble()
                Float::class -> dataInputStream.readFloat()
                Char::class -> dataInputStream.readChar()
                Boolean::class -> dataInputStream.readBoolean()
                String::class -> dataInputStream.readUTF()
                else -> return null
            }
        }.toTypedArray())
    }

    inline fun <reified T : Any> createInstance(dataInputStream: DataInput): T? =
        createInstance(dataInputStream, T::class)

}