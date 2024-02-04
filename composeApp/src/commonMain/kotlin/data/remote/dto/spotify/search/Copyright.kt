package data.remote.dto.spotify.search

import kotlinx.serialization.Serializable

@Serializable
data class Copyright(
    val text: String? = null,
    val type: String? = null,
)
