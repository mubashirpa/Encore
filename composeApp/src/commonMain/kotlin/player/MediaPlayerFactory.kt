package player

expect class MediaPlayerFactory {
    fun createMediaPlayer(playerState: PlayerState): MediaPlayer
}
