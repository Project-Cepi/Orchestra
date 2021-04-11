package world.cepi.orchestra.song

class LoopOptions(
    /** If this song is looping or not */
    val isLooping: Boolean,
    /** How many times to loop. 0 is infinite. */
    val loopCount: Byte,
    /** What tick it should loop to. */
    val loopStartTick: Short
)