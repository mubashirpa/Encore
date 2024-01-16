package data.remote.dto.category

import kotlinx.serialization.Serializable

@Serializable
data class Icon(
    val height: Int? = null,
    val url: String? = null,
    val width: Int? = null
)