package data.remote.dto.spotify.playlists

import kotlinx.serialization.Serializable

@Serializable
data class PlaylistsDto(
    val message: String? = null,
    val playlists: Playlists? = null,
)
