package world.cepi.orchestra.command

import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentEnum
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import net.minestom.server.utils.Vector
import net.minestom.server.utils.entity.EntityFinder
import net.minestom.server.utils.location.RelativeVec
import net.minestom.server.utils.math.FloatRange
import world.cepi.kstom.command.addSyntax
import kotlin.random.Random

object PlaySoundCommand : Command("playsound") {

    init {

        val sound = ArgumentSound("sound")

        val volume = ArgumentType.FloatRange("volume").setDefaultValue(FloatRange(1f, 1f)).map {

            if (it.minimum == it.maximum)
                return@map it.minimum

            Random.Default.nextDouble(it.minimum.toDouble(), it.maximum.toDouble()).toFloat()
        }

        val pitch = ArgumentType.FloatRange("pitch").setDefaultValue(FloatRange(1f, 1f)).map {

            if (it.minimum == it.maximum)
                return@map it.minimum

            Random.Default.nextDouble(it.minimum.toDouble(), it.maximum.toDouble()).toFloat()
        }

        val targets = ArgumentType.Entity("target").setDefaultValue {
            EntityFinder().apply {
                setTargetSelector(EntityFinder.TargetSelector.SELF)
            }
        }

        val location = ArgumentType.RelativeVec3("source")
            .setDefaultValue(RelativeVec(Vector(.0, .0, .0), true, true, true))

        val soundCategory = ArgumentType.Enum("category", Sound.Source::class.java).also {
            it.setFormat(ArgumentEnum.Format.LOWER_CASED)
        }.setDefaultValue(Sound.Source.MASTER)

        addSyntax(sound, volume, pitch, targets, location, soundCategory) {
            val createdSound = Sound.sound(
                context[sound],
                context[soundCategory],
                context[volume], context[pitch]
            )

            context[targets].find(sender).forEach {
                (it as? Player)?.playSound(createdSound)
            }
        }

    }

}