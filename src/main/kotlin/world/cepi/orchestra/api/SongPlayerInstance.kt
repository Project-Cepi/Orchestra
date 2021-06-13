package world.cepi.orchestra.api

import net.minestom.server.entity.Player
import net.minestom.server.timer.Task
import net.minestom.server.utils.time.TimeUnit
import net.minestom.server.utils.time.UpdateOption
import world.cepi.kstom.Manager
import world.cepi.kstom.util.component1
import world.cepi.kstom.util.component2
import world.cepi.kstom.util.component3
import world.cepi.orchestra.api.data.formatted.CustomInstrument
import world.cepi.orchestra.api.data.formatted.SongLayer
import world.cepi.orchestra.api.data.formatted.SongMap

class SongPlayerInstance(
    val map: SongMap,
    val player: Player,
    tempo: Double,
    val customInstruments: List<CustomInstrument> = listOf(),
    val layers: List<SongLayer> = listOf()
) {

    var tempo: Double = tempo
        set(value) {
            field = value
            resume()
        }

    var step = 0

    var task: Task? = null

    fun resume() {
        stop()

        task = Manager.scheduler.buildTask {

            val (x, y, z) = player.position

            map.map[step]?.forEach { notePair ->
                notePair.value.playToAudience(
                    player,
                    x, y, z,
                    customInstruments,
                    layers[notePair.key]
                )
            }

            step++
        }
            .repeat(UpdateOption((20 / tempo).toLong(), TimeUnit.TICK))
            .schedule()
    }

    fun stop() = task?.cancel()

    init {
        resume()
    }

}