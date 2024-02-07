package presentation.playlist

import core.Result
import domain.model.playlists.Playlist

data class PlaylistUiState(
    val playlistId: String = "",
    val playlistImageUrl: String = "",
    val playlistItemsResult: Result<Playlist> = Result.Empty(),
)
