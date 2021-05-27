package world.cepi.orchestra

class SongHeader(formatBytes: Short, nbsVersion: Byte, vanillaInstrumentCount: Byte, songLength: Short) {
    val formatBytes = formatBytes
    val nbsVersion = nbsVersion
    val vanillaInstrumentCount = vanillaInstrumentCount
    val songLength = songLength

    override fun toString(): String {
        return "Format: ${formatBytes}\nNBS Version: ${nbsVersion}\nVanilla Instrument Count: ${vanillaInstrumentCount}\nSong Length: ${songLength}"
    }
}