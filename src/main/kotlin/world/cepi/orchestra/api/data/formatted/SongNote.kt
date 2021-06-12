package world.cepi.orchestra.api.data.formatted

import net.kyori.adventure.audience.Audience
import net.kyori.adventure.sound.Sound
import net.minestom.server.sound.SoundEvent
import java.io.DataInput
import kotlin.math.pow

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

        audience.playSound(Sound.sound(instrument, Sound.Source.VOICE, volume / 100.toFloat(),
            noteBlockPitchToMinecraftPitch(key)
        ), x, y, z)
    }

    companion object {

        const val min = 33.toShort()
        const val max = 57.toShort()

        fun mapFromStream(dataStream: DataInput): SongMap {

            val map = SongMap()

            var tickJump = -1
            while (dataStream.readShort().also { tickJump += it } != 0.toShort()) {

                var layerJump = -1
                while (dataStream.readShort().also { layerJump += it } != 0.toShort()) {
                    val note = SongNote(
                        dataStream.readByte(),
                        dataStream.readByte(),
                        dataStream.readByte(),
                        dataStream.readByte(),
                        dataStream.readShort()
                    )

                    map[tickJump, layerJump] = note
                }
            }

            return map
        }

        fun noteBlockPitchToMinecraftPitch(pitch: Byte): Float {
            return (2.toDouble().pow(((pitch.coerceIn(min.toByte(), max.toByte()).toDouble() - min) - 12) / 12)).toFloat()
        }

    }
}