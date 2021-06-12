package world.cepi.orchestra.api.data.formatted

import net.kyori.adventure.audience.Audience
import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import net.minestom.server.sound.SoundEvent
import java.io.DataInput
import kotlin.math.pow

/**
 * Represents a song note that can be played to an [Audience]
 */
data class SongNote(
    /** The instrument of this note. */
    val instrument: Byte,
    val key: Byte,
    val volume: Byte,
    val panning: Byte,
    val pitch: Short
) {

    fun sound(customIntruments: List<CustomInstrument>): Key {
        return SongKeyMap[instrument.toInt()]?.key()
            ?: if (customIntruments.size > instrument - 15) customIntruments[instrument - 15].name else null
            ?: SoundEvent.NOTE_BLOCK_PLING.key()
    }

    fun playToAudience(
        audience: Audience,
        x: Double,
        y: Double,
        z: Double,
        customIntruments: List<CustomInstrument>
    ) {

        audience.playSound(
            Sound.sound(
                sound(customIntruments), Sound.Source.VOICE, volume / 100.toFloat(), noteBlockPitchToMinecraftPitch(key)
            ), x, y, z
        )
    }

    companion object {

        /**
         * The minimum minecraft-playable note block pitch.
         */
        const val min = 33.toShort()

        /**
         * The maximum minecraft-playable note block pitch.
         */
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

        /**
         * Turns a key (0-87, clamped to [min]-[max] for playability purposes)
         * into a minecraft pitch value (0.5f to 2f)
         *
         * @param pitch The pitch (0-87)
         *
         * @return The resulting float with the calculation 2^((pitch - min - 12)/12)
         */
        fun noteBlockPitchToMinecraftPitch(pitch: Byte): Float {
            return (2.toDouble().pow(((pitch.coerceIn(min.toByte(), max.toByte()).toDouble() - min) - 12) / 12)).toFloat()
        }

    }
}