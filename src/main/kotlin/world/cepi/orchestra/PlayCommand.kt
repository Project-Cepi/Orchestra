package world.cepi.orchestra

import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.literal
import java.io.File

object PlayCommand: Command("orchestra", "orch") {
    init {
        val play = "play".literal()
        val songName = ArgumentType.Word("song-name")

        addSyntax(play, songName) { sender, args ->
            val player = sender as Player
            val file = File(Orchestra.folderDir + "\\${args[songName]}.nbs")
            if (file.exists()) {

                // TODO should probably cache this
                val song = Song.from(file)

                song.play(player)

                player.sendMessage("Song played successfully!")
            } else {
                player.sendMessage("That file does not exist!")
            }
        }
    }
}