package world.cepi.orchestra

data class RawSongHeader(
    val newFormat: Short,
    val nbsVersion: Byte,
    val vanillaInstrumentCount: Byte,
    val songLength: Short,
    val layerCount: Short,
    val songName: String,
    val songAuthor: String,
    val songOriginalAuthor: String,
    val songDescription: String,
    val songTempo: Short,
    val autoSaving: Byte,
    val autoSavingDuration: Byte,
    val timeSignature: Byte,
    val minutesSpent: Int,
    val leftClicks: Int,
    val rightClicks: Int,
    val noteBlocksAdded: Int,
    val noteBlocksRemoved: Int,
    val midiOrSchematicFileName: String,
    val loop: Byte,
    val maxLoopCount: Byte,
    val loopStartTick: Short
)