package world.cepi.orchestra.data.raw

import world.cepi.orchestra.util.ConstructorStreamParser
import world.cepi.orchestra.util.EndianDataInputStream

data class RawSongNote(
    val tickJumps: Short,
    val layerJumps: Short,
    val instrument: Byte,
    val key: Byte,
    val volume: Byte,
    val panning: Byte,
    val pitch: Byte
) {

    companion object {
        fun fromDataStream(dataInputStream: EndianDataInputStream) = ConstructorStreamParser.createInstance<RawSongNote>(dataInputStream)!!
    }

}