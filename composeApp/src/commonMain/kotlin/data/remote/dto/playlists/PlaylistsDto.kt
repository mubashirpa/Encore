package data.remote.dto.playlists

import kotlinx.serialization.Serializable

@Serializable
data class PlaylistsDto(
    val message: String? = "",
    val playlists: Playlists? = Playlists()
)