package presentation.library

import core.Result
import domain.model.playlists.Playlist
import domain.model.spotify.users.followedArtists.FollowedArtistsItem
import domain.model.spotify.users.usersTopItems.UsersTopTrackItem

data class LibraryUiState(
    val accessToken: String = "",
    val followedArtists: Result<List<FollowedArtistsItem>> = Result.Empty(),
    val usersPlaylists: Result<List<Playlist>> = Result.Empty(),
    val usersTopTracks: Result<List<UsersTopTrackItem>> = Result.Empty(),
)
