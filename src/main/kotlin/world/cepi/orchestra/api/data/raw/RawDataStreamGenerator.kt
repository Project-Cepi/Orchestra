package world.cepi.orchestra.api.data.raw

import world.cepi.orchestra.api.util.ConstructorStreamParser
import world.cepi.orchestra.api.util.EndianDataInputStream
import kotlin.reflect.KClass

open class RawDataStreamGenerator<T : Any>(val clazz: KClass<T>) {

    fun fromDataStream(dataInputStream: EndianDataInputStream) = ConstructorStreamParser.createInstance(dataInputStream, clazz)!!
}