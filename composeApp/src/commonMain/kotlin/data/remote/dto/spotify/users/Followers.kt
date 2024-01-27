package data.remote.dto.spotify.users

import kotlinx.serialization.Serializable

@Serializable
data class Followers(
    val href: String? = null,
    val total: Int? = null,
)
