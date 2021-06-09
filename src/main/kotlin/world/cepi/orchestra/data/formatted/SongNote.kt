package world.cepi.orchestra.data.formatted

import net.kyori.adventure.audience.Audience
import net.kyori.adventure.sound.Sound
import net.minestom.server.entity.Player
import net.minestom.server.sound.SoundEvent
import java.lang.IllegalArgumentException
import javax.sound.midi.MidiSystem
import javax.sound.midi.Receiver
import javax.sound.midi.ShortMessage

data class SongNote(
    val instrument: SoundEvent,
    val key: Byte,
    val volume: Byte,
    val panning: Byte,
    val pitch: Byte
) {

    constructor(
        instrument: Byte,
        key: Byte,
        volume: Byte,
        panning: Byte,
        pitch: Byte
    ): this(
        SongKeyMap[instrument.toInt()] ?: throw IllegalArgumentException("Song instrument does not exist"),
        key, volume, panning, pitch
    )

    fun playToAudience(audience: Audience, x: Double, y: Double, z: Double) {

        // TODO volume / pitch
        audience.playSound(Sound.sound(instrument, Sound.Source.MUSIC, 1f, 1f), x, y, z)
    }
}