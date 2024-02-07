package presentation.library

import core.Result
import domain.model.artists.Artist
import domain.model.playlists.Playlist
import domain.model.tracks.Track

data class LibraryUiState(
    val accessToken: String = "",
    val followedArtists: Result<List<Artist>> = Result.Empty(),
    val usersPlaylists: Result<List<Playlist>> = Result.Empty(),
    val usersTopTracks: Result<List<Track>> = Result.Empty(),
)
