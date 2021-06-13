package world.cepi.orchestra.api.data.raw

import world.cepi.orchestra.api.util.ConstructorStreamParser
import world.cepi.orchestra.api.util.EndianDataInputStream
import java.io.DataInput
import kotlin.reflect.KClass

open class RawDataStreamGenerator<T : Any>(val clazz: KClass<T>) {

    fun fromDataStream(dataInputStream: DataInput) = ConstructorStreamParser.createInstance(dataInputStream, clazz)!!
}