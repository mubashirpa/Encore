package data.remote.dto.users

import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val height: Int? = 0,
    val url: String = "",
    val width: Int? = 0
)