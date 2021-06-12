package world.cepi.orchestra.api

import net.minestom.server.entity.Player
import world.cepi.orchestra.api.data.formatted.SongHeader
import world.cepi.orchestra.api.data.formatted.SongMap
import world.cepi.orchestra.api.data.formatted.SongNote
import world.cepi.orchestra.api.data.raw.RawSongHeader
import world.cepi.orchestra.api.util.EndianDataInputStream
import java.nio.file.Path
import kotlin.io.path.inputStream

class Song(
    val songHeader: SongHeader,
    val songMap: SongMap
) {

    fun play(player: Player): SongPlayerInstance =
        songMap.play(player, songHeader.songTempo)

    companion object {
        fun from(path: Path): Song {
            val inputStream = EndianDataInputStream(path.inputStream())

            val songHeader = SongHeader.fromRawHeader(RawSongHeader.fromDataStream(inputStream))

            val map = SongNote.mapFromStream(inputStream)

            return Song(songHeader, map)
        }
    }
}