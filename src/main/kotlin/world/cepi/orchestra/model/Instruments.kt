package world.cepi.orchestra.model

import net.minestom.server.sound.Sound

enum class Instrument(val sound: Sound) {
    PIANO(TODO()),
    DOUBLE_BASE(Sound.BLOCK_NOTE_BLOCK_BASS),
    BASE_DRUM(Sound.BLOCK_NOTE_BLOCK_BASEDRUM),
    SNARE_DRUM(Sound.BLOCK_NOTE_BLOCK_BASEDRUM),
    CLICK(Sound.BLOCK_NOTE_BLOCK_HAT),
    GUITAR(Sound.BLOCK_NOTE_BLOCK_GUITAR),
    FLUTE(Sound.BLOCK_NOTE_BLOCK_FLUTE),
    BELL(Sound.BLOCK_NOTE_BLOCK_BELL),
    CHIME(Sound.BLOCK_NOTE_BLOCK_CHIME),
    XYLOPHONE(Sound.BLOCK_NOTE_BLOCK_XYLOPHONE),
    IRON_XYLOPHONE(Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE),
    COW_BELL(Sound.BLOCK_NOTE_BLOCK_COW_BELL),
    DIDGERIDOO(Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO),
    BIT(Sound.BLOCK_NOTE_BLOCK_BIT),
    BANJO(Sound.BLOCK_NOTE_BLOCK_BANJO),
    PLING(Sound.BLOCK_NOTE_BLOCK_PLING);

    companion object {
        private val instrumentBytes: Map<Byte, Instrument> = mapOf(
            0.toByte() to PIANO,
            1.toByte() to DOUBLE_BASE,
            2.toByte() to BASE_DRUM,
            3.toByte() to SNARE_DRUM,
            4.toByte() to CLICK,
            5.toByte() to GUITAR,
            6.toByte() to FLUTE,
            7.toByte() to BELL,
            8.toByte() to CHIME,
            9.toByte() to XYLOPHONE,
            10.toByte() to IRON_XYLOPHONE,
            11.toByte() to COW_BELL,
            12.toByte() to DIDGERIDOO,
            13.toByte() to BIT,
            14.toByte() to BANJO,
            15.toByte() to PLING
        )

        fun getInstrument(instrument: Byte): Instrument = instrumentBytes[instrument] ?: throw NumberFormatException("Instrument must be between 0 and 15")
    }
}