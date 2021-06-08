package world.cepi.orchestra.play

import org.junit.jupiter.api.Test
import world.cepi.orchestra.data.raw.RawSongHeader
import java.io.DataInputStream
import java.io.InputStream




class HeaderParsingTest {

    @Test
    fun `make sure headers are parsed correctly`() {
        val resource = this.javaClass.getResource("/super_mario_test.nbs");

        val dataInputStream = DataInputStream(resource.openStream())

        val header = RawSongHeader.fromDataStream(dataInputStream)

        println(header)

        assert(true)
    }

}