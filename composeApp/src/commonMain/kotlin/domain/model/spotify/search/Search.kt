package domain.model.spotify.search

import domain.model.artists.Artist
import domain.model.playlists.Playlist
import domain.model.tracks.Track

data class Search(
    val albums: List<AlbumsItem>? = null,
    val artists: List<Artist>? = null,
    val playlists: List<Playlist>? = null,
    val shows: List<ShowsItem>? = null,
    val tracks: List<Track>? = null,
)
