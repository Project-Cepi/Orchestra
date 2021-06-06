package world.cepi.orchestra

/**
 * If the song is in an old format, throw an error.
 */
class OldSongError(message: String) : Error(message)