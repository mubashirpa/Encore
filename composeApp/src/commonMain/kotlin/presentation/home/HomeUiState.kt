package presentation.home

import core.Result
import domain.model.playlists.PlaylistsItem
import domain.model.users.UsersTopTrackItem

data class HomeUiState(
    val accessToken: String = "",
    val featuredPlaylistsResult: Result<List<PlaylistsItem>> = Result.Empty(),
    val usersTopTracksResult: Result<List<UsersTopTrackItem>> = Result.Empty()
)