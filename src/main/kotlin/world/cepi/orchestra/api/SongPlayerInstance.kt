package world.cepi.orchestra.api

import net.minestom.server.entity.Player
import net.minestom.server.timer.Task
import net.minestom.server.utils.time.TimeUnit
import net.minestom.server.utils.time.UpdateOption
import world.cepi.kstom.Manager
import world.cepi.orchestra.api.data.formatted.SongMap

class SongPlayerInstance(
    val map: SongMap,
    val player: Player,
    tempo: Double
) {

    var tempo: Double = tempo
        set(value) {
            field = value
            update()
        }

    var step = 0

    var task: Task? = null

    private fun update() {
        cancel()

        task = Manager.scheduler.buildTask {
            map.map[step]?.values?.forEach { note ->
                note.playToAudience(player, player.position.x, player.position.y, player.position.z)
            }

            step++
        }
            .repeat(UpdateOption((20 / tempo).toLong(), TimeUnit.TICK))
            .schedule()
    }

    fun cancel() = task?.cancel()

    init {
        update()
    }

}