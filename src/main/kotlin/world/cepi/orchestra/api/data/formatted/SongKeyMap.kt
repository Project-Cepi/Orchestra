package world.cepi.orchestra.api.data.formatted

import net.kyori.adventure.key.Key

internal object SongKeyMap {

    private val map = arrayOf(
        Key.key("minecraft:block.note_block.harp"),
        Key.key("minecraft:note_block_bass"),
        Key.key("minecraft:note_block_basedrum"),
        Key.key("minecraft:note_block_snare"),
        Key.key("minecraft:note_block_hat"),
        Key.key("minecraft:note_block_guitar"),
        Key.key("minecraft:note_block_flute"),
        Key.key("minecraft:note_block_bell"),
        Key.key("minecraft:note_block_chime"),
        Key.key("minecraft:note_block_xylophone"),
        Key.key("minecraft:note_block_iron_xylophone"),
        Key.key("minecraft:note_block_cow_bell"),
        Key.key("minecraft:note_block_didgeridoo"),
        Key.key("minecraft:note_block_bit"),
        Key.key("minecraft:note_block_banjo"),
        Key.key("minecraft:note_block_pling")
    )

    operator fun get(index: Int): Key? {
        if (index < 0) return null

        if (index >= map.size) return null

        return map[index]
    }

}