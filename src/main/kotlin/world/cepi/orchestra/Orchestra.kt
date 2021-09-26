package world.cepi.orchestra

import net.minestom.server.extensions.Extension;
import world.cepi.kstom.command.register
import world.cepi.kstom.command.unregister
import world.cepi.orchestra.command.FindSoundCommand
import world.cepi.orchestra.command.PlayCommand
import world.cepi.orchestra.command.PlaySoundCommand
import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.exists

class Orchestra : Extension() {

    override fun initialize() {
        PlayCommand.register()
        PlaySoundCommand.register()
        FindSoundCommand.register()

        val file = folderDir
        if (!file.exists())
            file.createDirectories()
        logger.info("[Orchestra] has been enabled!")
    }

    override fun terminate() {
        PlayCommand.unregister()
        PlaySoundCommand.unregister()
        FindSoundCommand.unregister()

        logger.info("[Orchestra] has been disabled!")
    }

    companion object {

        val folderDir = Path.of("extensions").resolve("Orchestra")
    }

}