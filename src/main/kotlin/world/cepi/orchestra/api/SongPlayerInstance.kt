package world.cepi.orchestra.api

import net.minestom.server.timer.Task

class SongPlayerInstance(private val task: Task) {

    fun cancel() = task.cancel()

}