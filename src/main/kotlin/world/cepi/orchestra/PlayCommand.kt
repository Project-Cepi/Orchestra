package world.cepi.orchestra

import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.literal
import world.cepi.orchestra.api.Song
import kotlin.io.path.exists

object PlayCommand : Command("orchestra", "orch") {
    init {

        val play = "play".literal()
        val stop = "stop".literal()

        val tempoLiteral = "tempo".literal()
        val tempo = ArgumentType.Double("tempoAmount").min(0.0).max(20.0)

        val songName = ArgumentType.Word("song-name")

        addSyntax(play, songName) { sender, args ->
            val player = sender as Player
            val file = Orchestra.folderDir.resolve("${args[songName]}.nbs")
            if (file.exists()) {

                // TODO should probably cache this
                val song = Song.from(file)

                val instance = song.play(player)

                GlobalPlayerManager[player] = instance

                player.sendMessage("Song played successfully!")
            } else {
                player.sendMessage("That file does not exist!")
            }
        }

        addSyntax(stop) { sender ->

            val player = sender as Player

            GlobalPlayerManager[player]?.cancel()

            player.sendMessage("Song stopped successfully!")
        }

        addSyntax(tempoLiteral, tempo) { sender, args ->
            val player = sender as Player

            GlobalPlayerManager[player]?.tempo = args[tempo]
        }
    }
}