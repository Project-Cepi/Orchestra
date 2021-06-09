package world.cepi.orchestra.play

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import world.cepi.orchestra.data.raw.RawSongHeader
import world.cepi.orchestra.util.EndianDataInputStream

class HeaderParsingTest {

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