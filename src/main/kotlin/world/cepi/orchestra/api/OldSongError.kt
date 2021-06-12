package world.cepi.orchestra.api

/**
 * If the song is in an old format, throw an error.
 */
class OldSongError(message: String) : Error(message)