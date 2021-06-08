package world.cepi.orchestra

import net.minestom.server.entity.Player
import world.cepi.orchestra.data.formatted.SongHeader
import world.cepi.orchestra.data.raw.RawSongHeader
import java.io.DataInputStream
import java.io.File
import java.io.FileInputStream

class Song(
    val songHeader: SongHeader
) {

    fun play(player: Player) {

    }

    companion object {
        fun from(file: File): Song {
            val inputStream = DataInputStream(FileInputStream(file))

            val songHeader = SongHeader.fromRawHeader(RawSongHeader.fromDataStream(inputStream))

            return Song(songHeader)
        }
    }
}