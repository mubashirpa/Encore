package presentation.home

import core.Result
import domain.model.playlists.Playlist
import domain.model.saavn.launchData.LaunchData
import domain.model.spotify.users.usersTopItems.UsersTopTrackItem

data class HomeUiState(
    val accessToken: String = "",
    val featuredPlaylistsResult: Result<List<Playlist>> = Result.Empty(),
    val launchDataResult: Result<LaunchData> = Result.Empty(),
    val usersTrackItemResult: Result<List<UsersTopTrackItem>> = Result.Empty(),
)
