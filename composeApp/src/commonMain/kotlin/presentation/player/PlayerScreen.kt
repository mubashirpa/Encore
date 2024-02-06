package presentation.player

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import core.Result
import core.utils.CryptoManager
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
    val cryptoManager = remember { CryptoManager() }

    LaunchedEffect(trackId) {
        onEvent(PlayerUiEvent.OnGetTrackId(trackId))
    }

    if (uiState.trackResult is Result.Success) {
        val track = uiState.trackResult.data
        val encryptedMediaUrl = track?.encryptedMediaUrl
        if (encryptedMediaUrl != null) {
            LaunchedEffect(encryptedMediaUrl) {
                val trackUrl = cryptoManager.decrypt(track.encryptedMediaUrl)
                playTrack(
                    trackUrl = trackUrl,
                    mediaPlayerController = mediaPlayerController,
                )
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        PlayerBar(modifier = Modifier.align(Alignment.BottomCenter))
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
