package data.remote.dto.spotify.search

import data.remote.dto.spotify.search.albums.Albums
import data.remote.dto.spotify.search.artists.Artists
import data.remote.dto.spotify.search.audiobooks.Audiobooks
import data.remote.dto.spotify.search.episodes.Episodes
import data.remote.dto.spotify.search.playlists.Playlists
import data.remote.dto.spotify.search.shows.Shows
import data.remote.dto.spotify.search.tracks.Tracks
import kotlinx.serialization.Serializable

@Serializable
data class SearchDto(
    val albums: Albums? = null,
    val artists: Artists? = null,
    val audiobooks: Audiobooks? = null,
    val episodes: Episodes? = null,
    val playlists: Playlists? = null,
    val shows: Shows? = null,
    val tracks: Tracks? = null,
)
