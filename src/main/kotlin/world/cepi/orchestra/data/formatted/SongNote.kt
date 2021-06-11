package world.cepi.orchestra.data.formatted

import net.kyori.adventure.audience.Audience
import net.kyori.adventure.sound.Sound
import net.minestom.server.entity.Player
import net.minestom.server.sound.SoundEvent
import java.io.DataInput
import java.lang.IllegalArgumentException
import javax.sound.midi.MidiSystem
import javax.sound.midi.Receiver
import javax.sound.midi.ShortMessage

data class SongNote(
    val instrument: SoundEvent,
    val key: Byte,
    val volume: Byte,
    val panning: Byte,
    val pitch: Short
) {

    constructor(
        instrument: Byte,
        key: Byte,
        volume: Byte,
        panning: Byte,
        pitch: Short
    ): this(
        SongKeyMap[instrument.toInt()] ?: SoundEvent.AMBIENT_UNDERWATER_EXIT, /* throw IllegalArgumentException("Song instrument does not exist"), */
        key, volume, panning, pitch
    )

    fun playToAudience(audience: Audience, x: Double, y: Double, z: Double) {

        // TODO volume / pitch
        audience.playSound(Sound.sound(instrument, Sound.Source.MUSIC, 1f, 1f), x, y, z)
    }

    companion object {
        fun listFromStream(dataStream: DataInput): List<SongNote> {

            val list = mutableListOf<SongNote>()

            while (dataStream.readShort() != 0.toShort()) {
                while (dataStream.readShort() != 0.toShort()) {
                    val note = SongNote(
                        dataStream.readByte(),
                        dataStream.readByte(),
                        dataStream.readByte(),
                        dataStream.readByte(),
                        dataStream.readShort()
                    )

                    list.add(note)
                }
            }

            return list
        }
    }
}