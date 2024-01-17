package data.remote.dto.playlists

import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val height: Int? = null,
    val url: String? = null,
    val width: Int? = null
)