package data.remote.dto.spotify.playlists.featuredPlaylists

import kotlinx.serialization.Serializable

@Serializable
data class FeaturedPlaylistsDto(
    val message: String? = null,
    val playlists: Playlists? = null,
)
