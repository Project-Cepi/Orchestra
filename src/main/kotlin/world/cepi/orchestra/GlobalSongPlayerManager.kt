package world.cepi.orchestra

import net.minestom.server.entity.Player
import world.cepi.orchestra.api.SongPlayerInstance

object GlobalSongPlayerManager {

    private val map = mutableMapOf<Player, SongPlayerInstance>()

    operator fun get(player: Player) = map[player]

    operator fun set(player: Player, instance: SongPlayerInstance) {
        map[player]?.stop()

        map[player] = instance
    }

    fun remove(player: Player) = map.remove(player)

}