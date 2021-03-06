package world.cepi.orchestra.play

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import world.cepi.orchestra.api.data.formatted.SongNote
import world.cepi.orchestra.api.data.raw.RawSongHeader
import world.cepi.orchestra.api.util.EndianDataInputStream

class SongParsingTest {

    @Test
    fun `make sure headers are parsed correctly`() {

        val resource = this.javaClass.getResource("/megalovania.nbs");

        val dataInputStream = EndianDataInputStream(resource.openStream())

        val header = RawSongHeader.fromDataStream(dataInputStream)

        assertEquals(0, header.newFormat)
        assertEquals(5, header.nbsVersion)
        assertEquals("Toby Fox", header.songOriginalAuthor)
    }

}