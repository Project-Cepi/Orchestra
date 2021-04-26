package world.cepi.orchestra.song

/** The "Header" of the song. Describes all properties of it. */
class SongProperties(
    /** The amount of vanilla instruments used (uniquely) */
    val vanillaInstrumentCount: Byte,
    /** The length of this song (per row, not layer / per block) */
    val length: Short,
    /** The amount of layers this song has */
    val layerCount: Short,
    /** The name of this song. */
    val name: String,
    /** The creator of this Note Block Song */
    val author: String,
    /** The creator of the original song (if this song isn't original) */
    val originalAuthor: String,
    /** The description of this song */
    val description: String,
    /** The tempo of this song measured in ticks per second. */
    val tempo: Double,
    /** The time signature of this song. Default is 4 */
    val timeSignature: Byte = 4,
    
    /** If this song is looping or not */
    val isLooping: Boolean,
    /** How many times to loop. 0 is infinite. */
    val loopCount: Byte,
    /** What tick it should loop to. */
    val loopStartTick: Short
)