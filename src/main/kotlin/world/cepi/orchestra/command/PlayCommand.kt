package world.cepi.orchestra.command

import net.kyori.adventure.sound.Sound
import net.kyori.adventure.sound.SoundStop
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import world.cepi.kepi.messages.sendFormattedTranslatableMessage
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.literal
import world.cepi.orchestra.GlobalSongPlayerManager
import world.cepi.orchestra.Orchestra
import world.cepi.orchestra.api.Song
import kotlin.io.path.exists

object PlayCommand : Command("orchestra") {
    init {

        val play = "play".literal()
        val stop = "stop".literal()

        val pause = "pause".literal()
        val resume = "resume".literal()

        val tempoLiteral = "tempo".literal()
        val tempo = ArgumentType.Double("tempoAmount").min(0.0).max(20.0)

        val songName = ArgumentType.Word("songName")

        addSyntax(play, songName) {
            val player = sender as Player
            val file = Orchestra.folderDir.resolve("${context[songName]}.nbs")
            if (file.exists()) {

                // TODO should probably cache this
                val song = Song.from(file)

                val header = song.header

                val instance = song.play(player)

                GlobalSongPlayerManager[player] = instance

                player.sendFormattedTranslatableMessage(
                    "orchestra", "play.now",
                    Component.text(header.name, NamedTextColor.BLUE),
                    Component.text(header.author, NamedTextColor.BLUE)
                )
            } else {
                player.sendFormattedTranslatableMessage("orchestra", "none", Component.text(context[songName], NamedTextColor.BLUE))
            }
        }

        addSyntax(stop) {

            val player = sender as Player

            val song = GlobalSongPlayerManager.remove(player)

            if (song == null) {
                player.sendFormattedTranslatableMessage("orchestra", "play.none")
                return@addSyntax
            }

            song.stop()

            player.stopSound(SoundStop.source(Sound.Source.VOICE))

            player.sendFormattedTranslatableMessage("orchestra", "stop", Component.text(song.header.name, NamedTextColor.BLUE))
        }

        addSyntax(pause) {
            val player = sender as Player

            val song = GlobalSongPlayerManager[player]

            if (song == null) {
                player.sendFormattedTranslatableMessage("orchestra", "play.none")
                return@addSyntax
            }

            song.stop()

            player.sendFormattedTranslatableMessage("orchestra", "pause", Component.text(song.header.name, NamedTextColor.BLUE))
        }

        addSyntax(resume) {
            val player = sender as Player

            val song = GlobalSongPlayerManager[player]

            if (song == null) {
                player.sendFormattedTranslatableMessage("orchestra", "play.none")
                return@addSyntax
            }

            song.resume()

            player.sendFormattedTranslatableMessage("orchestra", "resume", Component.text(song.header.name, NamedTextColor.BLUE))
        }

        addSyntax(tempoLiteral, tempo) {
            val player = sender as Player

            GlobalSongPlayerManager[player]?.tempo = context[tempo]
        }
    }
}