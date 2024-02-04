package presentation.library

import core.Result
import domain.model.spotify.playlists.PlaylistsItem
import domain.model.spotify.users.followedArtists.FollowedArtistsItem
import domain.model.spotify.users.usersTopItems.UsersTopTrackItem

data class LibraryUiState(
    val accessToken: String = "",
    val followedArtists: Result<List<FollowedArtistsItem>> = Result.Empty(),
    val usersPlaylists: Result<List<PlaylistsItem>> = Result.Empty(),
    val usersTopTracks: Result<List<UsersTopTrackItem>> = Result.Empty(),
)
