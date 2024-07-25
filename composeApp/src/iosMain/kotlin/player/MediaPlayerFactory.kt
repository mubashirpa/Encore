package player

actual class MediaPlayerFactory {
    actual fun createMediaPlayer(playerState: PlayerState): MediaPlayer = IOSMediaPlayer()
}
