package presentation.playlist

import core.Result
import domain.model.saavn.playlists.PlaylistItems

data class PlaylistUiState(
    val playlistItemsResult: Result<PlaylistItems> = Result.Empty(),
)
