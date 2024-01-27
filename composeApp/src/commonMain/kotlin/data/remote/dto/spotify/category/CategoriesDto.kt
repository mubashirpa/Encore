package data.remote.dto.spotify.category

import kotlinx.serialization.Serializable

@Serializable
data class CategoriesDto(
    val categories: Categories? = null,
)
