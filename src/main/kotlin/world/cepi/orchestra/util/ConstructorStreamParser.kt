package world.cepi.orchestra.util

import java.io.DataInputStream
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.full.valueParameters

object ConstructorStreamParser {

    inline fun <reified T : Any> createInstance(dataInputStream: DataInputStream): T? {
        val constructor = T::class.primaryConstructor ?: return null

        return constructor.call(*constructor.valueParameters.map {
            
            when (it.type.classifier as? KClass<*> ?: return null) {
                Byte::class -> dataInputStream.readByte()
                Short::class -> dataInputStream.readShort()
                Int::class -> dataInputStream.readInt()
                Long::class -> dataInputStream.readLong()
                Double::class -> dataInputStream.readDouble()
                Float::class -> dataInputStream.readFloat()
                Char::class -> dataInputStream.readChar()
                Boolean::class -> dataInputStream.readBoolean()
                String::class -> dataInputStream.readUTF()
                else -> return null
            }
        }.toTypedArray())
    }

}