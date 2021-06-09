package world.cepi.orchestra.data.raw

import world.cepi.orchestra.util.ConstructorStreamParser
import world.cepi.orchestra.util.EndianDataInputStream
import kotlin.reflect.KClass

open class RawDataStreamGenerator<T : Any>(val clazz: KClass<T>) {

    fun fromDataStream(dataInputStream: EndianDataInputStream) = ConstructorStreamParser.createInstance(dataInputStream, clazz)!!
}