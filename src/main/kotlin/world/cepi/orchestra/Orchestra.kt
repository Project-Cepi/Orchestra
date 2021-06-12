package world.cepi.orchestra

import net.minestom.server.extensions.Extension;
import world.cepi.kstom.Manager
import world.cepi.kstom.command.register
import world.cepi.kstom.command.unregister
import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.exists

class Orchestra : Extension() {

    override fun initialize() {
        PlayCommand.register()

        val file = folderDir
        if (!file.exists())
            file.createDirectories()
        logger.info("[Orchestra] has been enabled!")
    }

    override fun terminate() {
        PlayCommand.unregister()

        logger.info("[Orchestra] has been disabled!")
    }

    companion object {

        val folderDir = Path.of("extensions").resolve("Orchestra")
    }

}