package world.cepi.orchestra

import world.cepi.orchestra.data.raw.RawSongHeader
import java.io.DataInputStream
import java.io.File
import java.io.FileInputStream
import java.io.IOException

class Song {
    fun collectInfo(file: File) {
        try {
            val inputStream = DataInputStream(FileInputStream(file))

        } catch (ex: IOException) {
            print(ex.stackTrace)
        }

    }
}