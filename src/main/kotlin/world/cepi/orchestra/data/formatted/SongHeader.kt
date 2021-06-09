package world.cepi.orchestra.data.formatted

import world.cepi.orchestra.OldSongError
import world.cepi.orchestra.data.raw.RawSongHeader

data class SongHeader(
    val nbsVersion: Byte = 5,
    val songName: String = "Unknown",
    val songAuthor: String = "Unknown",
    val songOriginalAuthor: String = "Unknown",
    val songDescription: String = "A song",
    val songTempo: Short,
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
        fun fromRawHeader(rawSongHeader: RawSongHeader): SongHeader {
            if (rawSongHeader.newFormat != 0.toShort())
                throw OldSongError("This song is using the old NBS format!")

            return SongHeader(
                rawSongHeader.nbsVersion,
                rawSongHeader.songName,
                rawSongHeader.songAuthor,
                rawSongHeader.songOriginalAuthor,
                rawSongHeader.songDescription,
                rawSongHeader.songTempo,
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