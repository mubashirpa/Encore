package presentation.playlist

sealed class PlaylistUiEvent {
    data class OnGetPlaylistId(val playlistId: String) : PlaylistUiEvent()

    data object OnRetry : PlaylistUiEvent()
}
