package world.cepi.example.model

enum class Instrument {
    PIANO, DOUBLE_BASE, BASE, SNARE_DRUM, CLICK, GUITAR, FLUTE, BELL, CHIME, XYLOPHONE, IRON_XYLOPHONE, COW_BELL, DIDGERIDOO, BIT, BANJO, PLING;

    companion object {
        private val instrumentBytes: Map<Byte, Instrument> = mapOf(
            0.toByte() to PIANO,
            1.toByte() to DOUBLE_BASE,
            2.toByte() to BASE,
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