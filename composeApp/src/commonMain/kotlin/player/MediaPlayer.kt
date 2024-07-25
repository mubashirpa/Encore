package player

interface MediaPlayer {
    fun play()

    fun play(url: String)

    fun pause()

    fun stop()

    fun onPlaybackStateChanged(listener: (playbackState: PlaybackState) -> Unit)

    fun onPlayerError(listener: (message: String?) -> Unit)
}
