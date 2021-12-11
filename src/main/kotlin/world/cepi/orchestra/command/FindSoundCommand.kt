package world.cepi.orchestra.command

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.sound.SoundEvent
import world.cepi.kstom.command.kommand.Kommand

object FindSoundCommand : Kommand({
    val partialSound = ArgumentType.String("partialSound")

    syntax(partialSound) {
        val filteredSounds = SoundEvent.values().filter {
            it.name().lowercase()
                .contains((!partialSound).lowercase())
        }

        if (filteredSounds.isEmpty()) {

            return@syntax
        }

        filteredSounds.forEach {
            sender.sendMessage(
                Component.text(it.name(), NamedTextColor.GRAY)
                    .clickEvent(ClickEvent.suggestCommand("/playsound ${it.name()}"))
                    .hoverEvent(Component.text("Click to suggest command!", NamedTextColor.YELLOW))
            )
        }
    }

}, "findsound")