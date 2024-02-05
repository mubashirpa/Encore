package presentation.playlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import core.Result
import presentation.playlist.components.PlaylistListItem

@Composable
fun PlaylistScreen(
    uiState: PlaylistUiState,
    onEvent: (PlaylistUiEvent) -> Unit,
    playlistId: String,
) {
    LaunchedEffect(playlistId) {
        onEvent(PlaylistUiEvent.OnGetPlaylistId(playlistId))
    }

    if (uiState.playlistItemsResult is Result.Success) {
        val playlistItems = uiState.playlistItemsResult.data?.list.orEmpty()
        if (playlistItems.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                content = {
                    items(
                        items = playlistItems,
                        key = { it.id!! },
                    ) { playlistItem ->
                        PlaylistListItem(
                            name = playlistItem.title.orEmpty(),
                            imageUrl = playlistItem.image.orEmpty(),
                            artists = playlistItem.subtitle.orEmpty(),
                            onClick = { /*TODO*/ },
                        )
                    }
                },
            )
        }
    }
}
