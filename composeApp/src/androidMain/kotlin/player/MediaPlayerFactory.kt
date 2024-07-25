package player

import android.content.Context

actual class MediaPlayerFactory(
    private val context: Context,
) {
    actual fun createMediaPlayer(playerState: PlayerState): MediaPlayer = AndroidMediaPlayer(context, playerState)
}
