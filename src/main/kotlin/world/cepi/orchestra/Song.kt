package world.cepi.orchestra

import net.minestom.server.entity.Player
import world.cepi.orchestra.data.formatted.SongHeader
import world.cepi.orchestra.data.raw.RawSongHeader
import world.cepi.orchestra.util.EndianDataInputStream
import java.io.File
import java.io.FileInputStream

class Song(
    val songHeader: SongHeader
) {

    fun play(player: Player) {

    }

    companion object {
        fun from(file: File): Song {
            val inputStream = EndianDataInputStream(FileInputStream(file))

            val songHeader = SongHeader.fromRawHeader(RawSongHeader.fromDataStream(inputStream))

            return Song(songHeader)
        }
    }
}