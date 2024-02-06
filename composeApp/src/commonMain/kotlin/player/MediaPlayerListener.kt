package player

interface MediaPlayerListener {
    fun onReady()

    fun onVideoCompleted()

    fun onError()
}
