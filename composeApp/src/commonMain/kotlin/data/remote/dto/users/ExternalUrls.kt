package data.remote.dto.users

import kotlinx.serialization.Serializable

@Serializable
data class ExternalUrls(
    val spotify: String? = null
)