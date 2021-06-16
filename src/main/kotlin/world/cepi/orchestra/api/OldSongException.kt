package world.cepi.orchestra.api

/**
 * If the song is in an old format, throw an error.
 */
class OldSongException(message: String) : Exception(message)