package data.remote.dto.category

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    val categories: Categories? = null
)