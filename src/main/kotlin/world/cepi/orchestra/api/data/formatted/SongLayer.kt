package world.cepi.orchestra.api.data.formatted

import world.cepi.orchestra.api.data.raw.RawDataStreamGenerator

class SongLayer(
    val name: String,
    val lock: Byte,
    val volume: Byte,
    val stereo: Byte
) {

    companion object: RawDataStreamGenerator<SongLayer>(SongLayer::class)

}