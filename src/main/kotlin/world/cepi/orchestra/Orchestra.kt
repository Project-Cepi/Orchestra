package world.cepi.orchestra

import net.minestom.server.MinecraftServer
import net.minestom.server.extensions.Extension;
import world.cepi.kstom.command.register
import java.io.File

class Orchestra : Extension() {

    val folderDir: String = "extensions\\Orchestra"

    override fun initialize() {
        PlayCommand.register()
        val file = File("extensions\\Orchestra")
        if (!file.exists()) {
            file.mkdirs()
        }
        logger.info("[Orchestra] has been enabled!")
    }

    override fun terminate() {
        logger.info("[Orchestra] has been disabled!")
    }

}