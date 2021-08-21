package world.cepi.orchestra.api.data.formatted

import net.kyori.adventure.audience.Audience
import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import net.minestom.server.sound.SoundEvent
import world.cepi.orchestra.api.data.raw.RawDataStreamGenerator
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

    /**
     * Gets the [Key] to be played to the player
     *
     * @param customInstruments The custom instruments to look at if the index goes over the normal notes
     *
     * @return the [Key] to be played to the player.
     */
    private fun sound(customInstruments: List<CustomInstrument> = listOf()): Key {
        return SongKeyMap[instrument.toInt()]
            ?: if (customInstruments.size > instrument - 15) customInstruments[instrument - 15].name else null
            ?: SoundEvent.BLOCK_NOTE_BLOCK_PLING.key()
    }

    /**
     * Plays this note to an audience.
     *
     * @param audience The audience to play it to.
     * @param x The x coordinate to play the note at
     * @param y The y coordinate to play the note at
     * @param z The z coordinate to play the note at
     * @param customInstruments The custom instruments this note needs, if any
     * @param layer The layer this note is playing on.
     */
    fun playToAudience(
        audience: Audience,
        x: Double,
        y: Double,
        z: Double,
        customInstruments: List<CustomInstrument> = listOf(),
        layer: SongLayer
    ) {

        audience.playSound(
            Sound.sound(
                sound(customInstruments),
                Sound.Source.VOICE,
                (layer.volume / 100) * (volume / 100.toFloat()),
                noteBlockKeyToMinecraftPitch(key)
            ), x, y, z
        )
    }

    companion object: RawDataStreamGenerator<SongNote>(SongNote::class) {

        /**
         * The minimum minecraft-playable note block pitch.
         */
        private const val min = 33.toShort()

        /**
         * The maximum minecraft-playable note block pitch.
         */
        private const val max = 57.toShort()

        /**
         * Turns a key (0-87, clamped to [min]-[max] for playability purposes)
         * into a minecraft pitch value (0.5f to 2f)
         *
         * @param key The key (0-87)
         *
         * @return The resulting float with the calculation 2^((pitch - min - 12)/12)
         */
        fun noteBlockKeyToMinecraftPitch(key: Byte, pitch: Short = 0): Float {
            return (2.toDouble().pow(((key.coerceIn(min.toByte(), max.toByte()).toDouble() - min) - 12 + (pitch / 200)) / 12)).toFloat()
        }

    }
}