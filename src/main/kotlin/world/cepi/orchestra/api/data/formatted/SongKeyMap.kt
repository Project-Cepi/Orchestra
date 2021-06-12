package world.cepi.orchestra.api.data.formatted

import net.minestom.server.sound.SoundEvent

object SongKeyMap {

    private val map = arrayOf(
        SoundEvent.NOTE_BLOCK_HARP,
        SoundEvent.NOTE_BLOCK_BASS,
        SoundEvent.NOTE_BLOCK_BASEDRUM,
        SoundEvent.NOTE_BLOCK_SNARE,
        SoundEvent.NOTE_BLOCK_HAT,
        SoundEvent.NOTE_BLOCK_GUITAR,
        SoundEvent.NOTE_BLOCK_FLUTE,
        SoundEvent.NOTE_BLOCK_BELL,
        SoundEvent.NOTE_BLOCK_CHIME,
        SoundEvent.NOTE_BLOCK_XYLOPHONE,
        SoundEvent.NOTE_BLOCK_IRON_XYLOPHONE,
        SoundEvent.NOTE_BLOCK_COW_BELL,
        SoundEvent.NOTE_BLOCK_DIDGERIDOO,
        SoundEvent.NOTE_BLOCK_BIT,
        SoundEvent.NOTE_BLOCK_BANJO,
        SoundEvent.NOTE_BLOCK_PLING
    )

    operator fun get(index: Int): SoundEvent? {
        if (index < 0) return null

        if (index >= map.size) return null

        return map[index]
    }

}