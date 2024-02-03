package presentation.home

import core.Result
import domain.model.saavn.LaunchData
import domain.model.spotify.playlists.PlaylistsItem
import domain.model.spotify.users.topItems.UsersTopTrackItem

data class HomeUiState(
    val accessToken: String = "",
    val featuredPlaylistsResult: Result<List<PlaylistsItem>> = Result.Empty(),
    val launchDataResult: Result<LaunchData> = Result.Empty(),
    val usersTrackItemResult: Result<List<UsersTopTrackItem>> = Result.Empty(),
)
