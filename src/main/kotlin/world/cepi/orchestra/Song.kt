package world.cepi.orchestra

import java.io.DataInputStream
import java.io.File
import java.io.FileInputStream
import java.io.IOException

class Song {
    fun collectInfo(file: File) {
        try {
            val inputStream = DataInputStream(FileInputStream(file))
            var byteRead: Int
            val formatByte = inputStream.read().toShort()
            val nbsVersion = inputStream.read().toByte()
            val vanillaInstrumentCount = inputStream.read().toByte()
            val songLength = inputStream.read().toShort()
            val songHeader = RawSongHeader(formatByte, nbsVersion, vanillaInstrumentCount, songLength)

            /*while (inputStream.read().also { byteRead = it } != -1) {
                //print(byteRead)
            }*/
            print(songHeader.toString())
        } catch (ex: IOException) {
            print(ex.stackTrace)
        }

    }
}