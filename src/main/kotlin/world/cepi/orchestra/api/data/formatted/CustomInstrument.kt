package world.cepi.orchestra.api.data.formatted

import net.kyori.adventure.key.Key
import world.cepi.orchestra.api.data.raw.RawCustomInstrument

class CustomInstrument(
    val name: Key,
    val pitch: Byte,
) {

    companion object {
        fun fromRawCustomInstrument(rawCustomInstrument: RawCustomInstrument): CustomInstrument =
            CustomInstrument(
                Key.key(rawCustomInstrument.name),
                rawCustomInstrument.pitch
            )

    }
}