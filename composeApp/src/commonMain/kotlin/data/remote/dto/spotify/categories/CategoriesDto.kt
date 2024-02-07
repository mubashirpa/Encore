package data.remote.dto.spotify.categories

import kotlinx.serialization.Serializable

@Serializable
data class CategoriesDto(
    val categories: Categories? = null,
)
