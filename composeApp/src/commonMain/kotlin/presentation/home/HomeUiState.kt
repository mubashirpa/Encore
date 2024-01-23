package presentation.home

import core.Result
import domain.model.spotify.playlists.PlaylistsItem
import domain.model.spotify.users.top_items.UsersTrackItem

data class HomeUiState(
    val accessToken: String = "",
    val featuredPlaylistsResult: Result<List<PlaylistsItem>> = Result.Empty(),
    val usersTrackItemResult: Result<List<UsersTrackItem>> = Result.Empty()
)