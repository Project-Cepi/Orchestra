package world.cepi.orchestra.data.raw

data class RawSongNote(
    val tickJumps: Short,
    val layerJumps: Short,
    val instrument: Byte,
    val key: Byte,
    val volume: Byte,
    val panning: Byte,
    val pitch: Byte
) {
}