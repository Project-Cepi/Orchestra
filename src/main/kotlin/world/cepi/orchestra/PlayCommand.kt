package world.cepi.orchestra

import net.kyori.adventure.sound.Sound
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.asSubcommand
import world.cepi.kstom.command.arguments.literal
import java.io.File

object PlayCommand: Command("orch"){
    init {
        val play = "play".literal()
        val songName = ArgumentType.Word("song-name")

        addSyntax(play, songName) {player, args ->
            val p = player as Player
            val file = File(Orchestra().folderDir + "\\${args.get(songName)}.nbs")
            if (file.exists()) {
                Song().collectInfo(file)
                p.sendMessage("Song played successfully!")
            } else {
                p.sendMessage("That file does not exist!")
            }
        }
    }
}