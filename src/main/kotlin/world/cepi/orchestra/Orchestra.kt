package world.cepi.orchestra

import net.minestom.server.MinecraftServer
import net.minestom.server.extensions.Extension;
import java.io.File

class Orchestra : Extension() {

    val folderDir: String = "extensions\\Orchestra"

    override fun initialize() {
        logger.info("[Orchestra] has been enabled!")
        MinecraftServer.getCommandManager().register(PlayCommand)
        val file = File("extensions\\Orchestra")
        if (!file.exists()) {
            file.mkdirs()
        }
    }

    override fun terminate() {
        logger.info("[Orchestra] has been disabled!")
    }

}