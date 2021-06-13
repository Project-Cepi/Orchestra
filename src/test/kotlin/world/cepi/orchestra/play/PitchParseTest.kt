package world.cepi.orchestra.play

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import world.cepi.orchestra.api.data.formatted.SongNote

class PitchParseTest {

    @Test
    fun `pitches should be read correctly`() {
        assertEquals(2f, SongNote.noteBlockKeyToMinecraftPitch(57))

        assertEquals(2f, SongNote.noteBlockKeyToMinecraftPitch(87))

        assertEquals(2f, SongNote.noteBlockKeyToMinecraftPitch(56, 200))

        assertEquals(.5f, SongNote.noteBlockKeyToMinecraftPitch(33))
    }

}