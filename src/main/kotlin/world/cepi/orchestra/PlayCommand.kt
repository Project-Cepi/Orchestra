package world.cepi.orchestra

import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.literal
import java.io.File

object PlayCommand: Command("orch"){
    init {
        val play = "play".literal()
        val songName = ArgumentType.Word("song-name")

        addSyntax(play, songName) {player, args ->
            val p = player as Player
            val file = File(Orchestra.plugin.folderDir + "\\${args.get(songName)}.nbs")
            if (file.exists()) {

                // TODO should probably cache this
                val song = Song.collectInfo(file)

                song.play()

                p.sendMessage("Song played successfully!")
            } else {
                p.sendMessage("That file does not exist!")
            }
        }
    }
}