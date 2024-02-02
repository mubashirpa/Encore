package domain.model.spotify.search

data class Search(
    val albums: List<AlbumsItem>? = null,
    val artists: List<ArtistsItem>? = null,
    val playlists: List<PlaylistsItem>? = null,
    val shows: List<ShowsItem>? = null,
    val tracks: List<TracksItem>? = null,
)
