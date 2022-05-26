package world.cepi.orchestra.command

import net.kyori.adventure.sound.Sound
import net.kyori.adventure.sound.SoundStop
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.builder.arguments.ArgumentType
import world.cepi.kepi.messages.sendFormattedTranslatableMessage
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.kommand.Kommand
import world.cepi.orchestra.GlobalSongPlayerManager
import world.cepi.orchestra.Orchestra
import world.cepi.orchestra.api.Song
import kotlin.io.path.exists

object PlayCommand : Kommand({
    val play = "play".literal()
    val stop = "stop".literal()

    val pause = "pause".literal()
    val resume = "resume".literal()

    val tempoLiteral = "tempo".literal()
    val tempo = ArgumentType.Double("tempoAmount").min(0.0).max(20.0)

    val songName = ArgumentType.Word("songName")

    syntax(play, songName) {
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
    }.onlyPlayers()

    syntax(stop) {

        val song = GlobalSongPlayerManager.remove(player)

        if (song == null) {
            player.sendFormattedTranslatableMessage("orchestra", "play.none")
            return@syntax
        }

        song.stop()

        player.stopSound(SoundStop.source(Sound.Source.VOICE))

        player.sendFormattedTranslatableMessage("orchestra", "stop", Component.text(song.header.name, NamedTextColor.BLUE))
    }.onlyPlayers()

    syntax(pause) {

        val song = GlobalSongPlayerManager[player]

        if (song == null) {
            player.sendFormattedTranslatableMessage("orchestra", "play.none")
            return@syntax
        }

        song.stop()

        player.sendFormattedTranslatableMessage("orchestra", "pause", Component.text(song.header.name, NamedTextColor.BLUE))
    }.onlyPlayers()

    syntax(resume) {
        val song = GlobalSongPlayerManager[player]

        if (song == null) {
            player.sendFormattedTranslatableMessage("orchestra", "play.none")
            return@syntax
        }

        song.resume()

        player.sendFormattedTranslatableMessage("orchestra", "resume", Component.text(song.header.name, NamedTextColor.BLUE))
    }.onlyPlayers()

    syntax(tempoLiteral, tempo) {
        GlobalSongPlayerManager[player]?.tempo = !tempo
    }.onlyPlayers()
}, "orchestra")