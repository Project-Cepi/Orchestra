package world.cepi.orchestra.data.formatted

import net.minestom.server.entity.Player
import javax.sound.midi.MidiSystem
import javax.sound.midi.Receiver
import javax.sound.midi.ShortMessage

data class SongNote(
    val instrument: Byte,
    val key: Byte,
    val volume: Byte,
    val panning: Byte,
    val pitch: Byte
) {
    fun playMinecraft(player: Player) {

    }

    fun playSynthesized() {
        val myMsg = ShortMessage()
        myMsg.setMessage(ShortMessage.NOTE_ON, 4, 60, 93)
        val synth = MidiSystem.getSynthesizer()
        val synthRcvr: Receiver = synth.receiver
        synthRcvr.send(myMsg, -1) // -1 means no time stamp
    }
}