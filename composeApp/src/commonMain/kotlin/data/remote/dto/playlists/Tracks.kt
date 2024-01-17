package data.remote.dto.playlists

import kotlinx.serialization.Serializable

@Serializable
data class Tracks(
    val href: String? = null,
    val total: Int? = null
)