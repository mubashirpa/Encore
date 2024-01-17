package data.remote.dto.users

import kotlinx.serialization.Serializable

@Serializable
data class Followers(
    val href: String? = null,
    val total: Int? = 0
)