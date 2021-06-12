package world.cepi.orchestra.api

import net.minestom.server.entity.Player
import world.cepi.orchestra.api.data.formatted.*
import world.cepi.orchestra.api.data.raw.RawCustomInstrument
import world.cepi.orchestra.api.data.raw.RawSongHeader
import world.cepi.orchestra.api.util.EndianDataInputStream
import java.nio.file.Path
import kotlin.io.path.inputStream

class Song(
    val header: SongHeader,
    val map: SongMap,
    val layers: List<SongLayer>,
    val customInstruments: List<CustomInstrument>
) {

    fun play(player: Player): SongPlayerInstance =
        map.play(player, header.tempo, customInstruments)

    companion object {
        fun from(path: Path): Song {
            val inputStream = EndianDataInputStream(path.inputStream())

            val songHeader = SongHeader.fromRawHeader(RawSongHeader.fromDataStream(inputStream))

            val map = SongNote.mapFromStream(inputStream)

            val layers = (1..songHeader.layerCount).map {
                SongLayer.fromDataStream(inputStream)
            }

            val customInstrumentAmount = inputStream.readByte()

            val customInstruments = (1..customInstrumentAmount).map {
                CustomInstrument.fromRawCustomInstrument(RawCustomInstrument.fromDataStream(inputStream))
            }

            return Song(songHeader, map, layers, customInstruments)
        }
    }
}