package data.remote.dto.spotify

import kotlinx.serialization.Serializable

@Serializable
data class Restrictions(
    val reason: String? = null,
)
