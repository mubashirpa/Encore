package domain.model.artists

import kotlinx.serialization.Serializable

@Serializable
data class Artist(
    val id: String? = null,
    val image: String? = null,
    val name: String? = null,
)
