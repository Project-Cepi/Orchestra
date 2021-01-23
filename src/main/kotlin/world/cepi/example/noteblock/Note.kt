package world.cepi.example.noteblock

import net.minestom.server.entity.Player
import net.minestom.server.sound.Sound
import net.minestom.server.sound.SoundCategory
import world.cepi.example.model.Instrument

/**
 * Detailed info about a note. This is specifically a relative note,
 * meaning it needs to be compared to the note before it.
 */
data class RelativeNote(
    /**
     * The amount of "jumps" to the next tick with at least one note block in it.
     * We start at tick -1. If the amount of jumps is 0,
     * the program will stop reading and proceed to the next part.
     */
    val tick_jumps: Short,
    /**
     * Once we have found an active tick,
     * we read the amount of vertical jumps to the next layer.
     * We start at layer -1. If this is 0,
     * we go back to step 1. If not, we have found a note block!
     */
    val layer_jumps: Short,
    /**
     * The instrument of the note block.
     * This is 0-15, or higher if the song uses custom instruments.
     */
    val instrument: Instrument,
    /**
     * The key of the note block, from 0-87, where 0 is A0 and 87 is C8.
     * 33-57 is within the 2-octave limit.
     */
    val key: Byte,
    /** The velocity/volume of the note block, from 0% to 100%. */
    val volume: Byte,
    /** The stereo position of the note block, from 0-200. 100 is center panning. */
    val panning: Byte,
    /**
     * The fine pitch of the note block, from -32,768 to 32,767 cents
     * (but the max in Note Block Studio is limited to -1200 and +1200).
     * 0 is no fine-tuning. ±100 cents is a single semitone difference.
     * After reading this, we go back to step 2.
     */
    val pitch: Short
) {
    init {
        if (key !in 0..87) throw NumberFormatException("Key must be between 0 and 87")
        if (volume !in 0..100) throw NumberFormatException("Volume must be between 0 and 100%")
        if (panning !in 0..200) throw NumberFormatException("Panning must be between 0 and 200")
    }

    fun play(player: Player) = player.playSound(instrument.sound, SoundCategory.MUSIC, volume.toFloat(), pitch.toFloat())
}