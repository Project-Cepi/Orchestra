package world.cepi.orchestra

import net.minestom.server.entity.Player
import net.minestom.server.utils.time.TimeUnit
import net.minestom.server.utils.time.UpdateOption
import world.cepi.kstom.Manager
import world.cepi.orchestra.data.formatted.SongHeader
import world.cepi.orchestra.data.formatted.SongMap
import world.cepi.orchestra.data.formatted.SongNote
import world.cepi.orchestra.data.raw.RawSongHeader
import world.cepi.orchestra.util.EndianDataInputStream
import java.nio.file.Path
import kotlin.io.path.inputStream

class Song(
    val songHeader: SongHeader,
    val songMap: SongMap
) {

    fun play(player: Player) {
        songMap.play(player, songHeader.songTempo)
    }

    companion object {
        fun from(path: Path): Song {
            val inputStream = EndianDataInputStream(path.inputStream())

            val songHeader = SongHeader.fromRawHeader(RawSongHeader.fromDataStream(inputStream))

            val map = SongNote.mapFromStream(inputStream)

            return Song(songHeader, map)
        }
    }
}