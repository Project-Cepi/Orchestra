package world.cepi.orchestra.data.raw

import java.io.DataInputStream

data class RawSongHeader(
    val newFormat: Short,
    val nbsVersion: Byte,
    val vanillaInstrumentCount: Byte,
    val songLength: Short,
    val layerCount: Short,
    val songName: String,
    val songAuthor: String,
    val songOriginalAuthor: String,
    val songDescription: String,
    val songTempo: Short,
    val autoSaving: Byte,
    val autoSavingDuration: Byte,
    val timeSignature: Byte,
    val minutesSpent: Int,
    val leftClicks: Int,
    val rightClicks: Int,
    val noteBlocksAdded: Int,
    val noteBlocksRemoved: Int,
    val midiOrSchematicFileName: String,
    val loop: Byte,
    val maxLoopCount: Byte,
    val loopStartTick: Short
) {
    companion object {
        fun fromDataStream(dataInputStream: DataInputStream) = with(dataInputStream) {
            return@with RawSongHeader(
                readShort(),
                readByte(),
                readByte(),
                readShort(),
                readShort(),
                readUTF(),
                readUTF(),
                readUTF(),
                readUTF(),
                readShort(),
                readByte(),
                readByte(),
                readByte(),
                readInt(),
                readInt(),
                readInt(),
                readInt(),
                readInt(),
                readUTF(),
                readByte(),
                readByte(),
                readShort()
            )
        }
    }
}