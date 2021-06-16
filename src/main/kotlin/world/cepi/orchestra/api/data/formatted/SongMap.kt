package world.cepi.orchestra.api.data.formatted

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import net.minestom.server.entity.Player
import world.cepi.orchestra.api.SongPlayerInstance
import java.io.DataInput

class SongMap {

    val map: MutableMap<Int, MutableMap<Int, SongNote>> = Int2ObjectOpenHashMap()

    operator fun get(x: Int, y: Int): SongNote? {
        if (map[x] == null) return null
        if (map[x]!![y] == null) return null

        return map[x]!![y]!!
    }

    operator fun set(x: Int, y: Int, note: SongNote) {
        if (map[x] == null) map[x] = Int2ObjectOpenHashMap()

        map[x]!![y] = note
    }

    fun play(
        player: Player,
        tempo: Double,
        customInstruments: List<CustomInstrument> = listOf(),
        layers: List<SongLayer> = listOf()
    ): SongPlayerInstance {
        return SongPlayerInstance(this, player, tempo, customInstruments, layers)
    }

    companion object {

        /**
         * Generates a [SongMap] instance from a [DataInput]
         *
         * @param dataStream The data stream to generate the [SongMap] from.
         *
         * @return The [SongMap] instance created by the data stream.
         */
        fun mapFromStream(dataStream: DataInput): SongMap {

            // Create an empty song map
            val map = SongMap()

            // Tick jumps always start at negative one
            var tickJump = -1

            // Open the tick jump loop -- ends when tickJump is 0
            while (dataStream.readShort().also { tickJump += it } != 0.toShort()) {

                // Layer jumps always start at negative one
                var layerJump = -1

                // Open the layer jump loop -- ends when layerJump is 0
                while (dataStream.readShort().also { layerJump += it } != 0.toShort()) {

                    // Generate a note from the remanining data in this note
                    val note = SongNote.fromDataStream(dataStream)

                    // Set it in the correct place in the song map.
                    map[tickJump, layerJump] = note
                }
            }

            return map
        }
    }

}