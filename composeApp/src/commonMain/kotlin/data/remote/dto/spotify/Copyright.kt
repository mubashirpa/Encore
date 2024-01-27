package data.remote.dto.spotify

import kotlinx.serialization.Serializable

@Serializable
data class Copyright(
    val text: String? = null,
    val type: String? = null,
)
