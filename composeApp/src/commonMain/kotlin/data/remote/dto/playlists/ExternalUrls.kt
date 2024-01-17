package data.remote.dto.playlists

import kotlinx.serialization.Serializable

@Serializable
data class ExternalUrls(
    val spotify: String? = null
)