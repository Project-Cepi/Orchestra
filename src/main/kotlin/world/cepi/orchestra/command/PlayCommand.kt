package world.cepi.orchestra.command

import net.kyori.adventure.sound.Sound
import net.kyori.adventure.sound.SoundStop
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
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

                player.sendMessage("Now playing: ${header.name} by ${header.author}")
            } else {
                player.sendMessage("That file does not exist!")
            }
        }

        addSyntax(stop) {

            val player = sender as Player

            GlobalSongPlayerManager.remove(player)?.stop()

            player.stopSound(SoundStop.source(Sound.Source.VOICE))

            player.sendMessage("Song stopped!")
        }

        addSyntax(pause) {
            val player = sender as Player

            GlobalSongPlayerManager[player]?.stop()

            player.sendMessage("Song paused!")
        }

        addSyntax(resume) {
            val player = sender as Player

            GlobalSongPlayerManager[player]?.resume()

            player.sendMessage("Song resumed!")
        }

        addSyntax(tempoLiteral, tempo) {
            val player = sender as Player

            GlobalSongPlayerManager[player]?.tempo = context[tempo]
        }
    }
}