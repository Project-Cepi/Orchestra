package world.cepi.orchestra.data.raw

data class RawSongNote(
    val tickJumps: UShort,
    val layerJumps: UShort,
    val instrument: UByte,
    val key: UByte,
    val volume: UByte,
    val panning: UByte,
    val pitch: Short
) {

    companion object: RawDataStreamGenerator<RawSongNote>(RawSongNote::class)

}