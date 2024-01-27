package data.remote.dto.spotify.search.playlists

import kotlinx.serialization.Serializable

@Serializable
data class Tracks(
    val href: String? = null,
    val total: Int? = null,
)
