package world.cepi.orchestra

import net.minestom.server.extensions.Extension;

class Orchestra : Extension() {

    override fun initialize() {
        logger.info("[Orchestra] has been enabled!")
    }

    override fun terminate() {
        logger.info("[Orchestra] has been disabled!")
    }

}