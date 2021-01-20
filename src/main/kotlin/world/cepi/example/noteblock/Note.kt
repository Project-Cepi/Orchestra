package world.cepi.example.noteblock

import net.minestom.server.entity.Player
import net.minestom.server.sound.Sound
import net.minestom.server.sound.SoundCategory
import world.cepi.example.model.Instrument

data class Note(
    val tick_jumps: Short,
    val layer_jumps: Short,
    val instrument: Instrument,
    val key: Byte,
    val volume: Byte,
    val panning: Byte,
    val pitch: Short
) {
    init {
        if (key !in 0..87) throw NumberFormatException("Key must be between 0 and 87")
        if (volume !in 0..100) throw NumberFormatException("Volume must be between 0 and 100%")
        if (panning !in 0..200) throw NumberFormatException("Panning must be between 0 and 200")
    }

    fun play(player: Player) = player.playSound(instrument.sound, SoundCategory.MUSIC, volume.toFloat(), pitch.toFloat())
}