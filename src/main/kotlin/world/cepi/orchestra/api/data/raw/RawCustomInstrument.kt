package world.cepi.orchestra.api.data.raw

class RawCustomInstrument(
    val name: String,
    val file: String,
    val pitch: Byte,
    val pressKey: Boolean
) {
    companion object: RawDataStreamGenerator<RawCustomInstrument>(RawCustomInstrument::class)
}