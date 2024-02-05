package presentation.playlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.Result
import presentation.search.components.TracksListItem

@Composable
fun PlaylistScreen(uiState: PlaylistUiState) {
    if (uiState.playlistItemsResult is Result.Success) {
        val tracks = uiState.playlistItemsResult.data?.list.orEmpty()
        if (tracks.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                content = {
                    items(
                        items = tracks,
                        key = { it.id!! },
                    ) { track ->
                        TracksListItem(
                            name = track.title.orEmpty(),
                            imageUrl = track.image.orEmpty(),
                            artists = track.subtitle.orEmpty(),
                            onClick = { /*TODO*/ },
                        )
                    }
                },
            )
        }
    }
}
