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
        map.play(player, header.tempo, customInstruments, layers, header)

    companion object {
        fun from(path: Path): Song {

            // Open the stream and put it in an EndianDataInputStream
            val inputStream = EndianDataInputStream(path.inputStream())

            // Generate the song header from the data stream
            val songHeader = SongHeader.fromRawHeader(RawSongHeader.fromDataStream(inputStream))

            // Generate the song map from the stirng
            val map = SongMap.mapFromStream(inputStream)

            // Map and generate all teh layers
            val layers = (1..songHeader.layerCount).map {
                SongLayer.fromDataStream(inputStream)
            }

            // Get the amount of custom instruments
            val customInstrumentAmount = inputStream.readByte()

            // Map and generate the custom instruments
            val customInstruments = (1..customInstrumentAmount).map {
                CustomInstrument.fromRawCustomInstrument(RawCustomInstrument.fromDataStream(inputStream))
            }

            return Song(songHeader, map, layers, customInstruments)
        }
    }
}