package world.cepi.orchestra.api.data.formatted

import world.cepi.orchestra.api.OldSongError
import world.cepi.orchestra.api.data.raw.RawSongHeader

data class SongHeader(
    val nbsVersion: Byte = 5,
    val name: String = "Unknown",
    val author: String = "Unknown",
    val originalAuthor: String = "Unknown",
    val description: String = "A song",
    val length: Short,
    val layerCount: Short,
    /** Tempo measured in ticks per second. */
    val tempo: Double,
    val timeSignature: Byte,
    val minutesSpent: Int,
    val leftClicks: Int,
    val rightClicks: Int,
    val noteBlocksAdded: Int,
    val noteBlocksRemoved: Int,
    val midiOrSchematicFileName: String,
    val loop: Boolean,
    val maxLoopCount: Byte,
    val loopStartTick: Short
) {
    companion object {
        fun fromRawHeader(rawSongHeader: RawSongHeader): SongHeader {
            if (rawSongHeader.newFormat != 0.toShort())
                throw OldSongError("This song is using the old NBS format!")

            return SongHeader(
                rawSongHeader.nbsVersion,
                rawSongHeader.songName,
                rawSongHeader.songAuthor,
                rawSongHeader.songOriginalAuthor,
                rawSongHeader.songDescription,
                rawSongHeader.songLength,
                rawSongHeader.layerCount,
                rawSongHeader.songTempo.toDouble() / 100,
                rawSongHeader.timeSignature,
                rawSongHeader.minutesSpent,
                rawSongHeader.leftClicks,
                rawSongHeader.rightClicks,
                rawSongHeader.noteBlocksAdded,
                rawSongHeader.noteBlocksRemoved,
                rawSongHeader.midiOrSchematicFileName,
                rawSongHeader.loop,
                rawSongHeader.maxLoopCount,
                rawSongHeader.loopStartTick
            )
        }
    }
}