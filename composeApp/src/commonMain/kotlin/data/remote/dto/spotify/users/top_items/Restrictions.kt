package data.remote.dto.spotify.users.top_items

import kotlinx.serialization.Serializable

@Serializable
data class Restrictions(
    val reason: String? = null,
)
