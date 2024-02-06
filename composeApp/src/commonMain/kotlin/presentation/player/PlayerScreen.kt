package presentation.player

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import core.Result
import org.koin.compose.koinInject
import player.MediaPlayerController
import player.MediaPlayerListener
import presentation.player.components.PlayerBar

@Composable
fun PlayerScreen(
    uiState: PlayerUiState,
    onEvent: (PlayerUiEvent) -> Unit,
    trackId: String,
) {
    val mediaPlayerController: MediaPlayerController = koinInject()

    LaunchedEffect(trackId) {
        onEvent(PlayerUiEvent.OnGetTrackId(trackId))
    }

    if (uiState.trackResult is Result.Success) {
        val track = uiState.trackResult.data
        if (track != null) {
            val mediaUrl = track.decryptedMediaUrl

            LaunchedEffect(mediaUrl) {
                if (mediaUrl != null) {
                    playTrack(
                        trackUrl = mediaUrl,
                        mediaPlayerController = mediaPlayerController,
                    )
                }
            }

            Box(modifier = Modifier.fillMaxSize()) {
                PlayerBar(
                    title = track.name.orEmpty(),
                    imageUrl = track.image.orEmpty(),
                    modifier = Modifier.align(Alignment.BottomCenter),
                    onPlayClicked = {
                        if (mediaPlayerController.isPlaying()) {
                            mediaPlayerController.pause()
                        } else {
                            mediaPlayerController.start()
                        }
                    },
                )
            }
        }
    }
}

private fun playTrack(
    trackUrl: String,
    mediaPlayerController: MediaPlayerController,
) {
    mediaPlayerController.prepare(
        pathSource = trackUrl,
        listener =
            object : MediaPlayerListener {
                override fun onReady() {
                    mediaPlayerController.start()
                }

                override fun onVideoCompleted() {
                }

                override fun onError() {
                }
            },
    )
}
