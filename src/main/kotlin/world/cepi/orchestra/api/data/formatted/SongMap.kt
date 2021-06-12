package world.cepi.orchestra.api.data.formatted

import net.minestom.server.entity.Player
import net.minestom.server.utils.time.TimeUnit
import net.minestom.server.utils.time.UpdateOption
import world.cepi.kstom.Manager
import world.cepi.orchestra.api.SongPlayerInstance

class SongMap {

    val map = mutableMapOf<Int, MutableMap<Int, SongNote>>()

    operator fun get(x: Int, y: Int): SongNote? {
        if (map[x] == null) return null
        if (map[x]!![y] == null) return null

        return map[x]!![y]!!
    }

    operator fun set(x: Int, y: Int, note: SongNote) {
        if (map[x] == null) map[x] = mutableMapOf()

        map[x]!![y] = note
    }

    fun play(player: Player, tempo: Double): SongPlayerInstance {
        return SongPlayerInstance(this, player, tempo)
    }


}