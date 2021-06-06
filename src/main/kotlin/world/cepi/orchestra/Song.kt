package world.cepi.orchestra

import world.cepi.orchestra.data.formatted.SongHeader
import world.cepi.orchestra.data.raw.RawSongHeader
import java.io.DataInputStream
import java.io.File
import java.io.FileInputStream

class Song(
    val songHeader: SongHeader
) {

    fun play() {

    }

    companion object {
        fun collectInfo(file: File): Song {
            val inputStream = DataInputStream(FileInputStream(file))

            val songHeader = SongHeader.fromRawHeader(RawSongHeader.fromDataStream(inputStream))

            return Song(songHeader)
        }
    }
}