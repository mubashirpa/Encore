package data.remote.dto.spotify.search.shows

import kotlinx.serialization.Serializable

@Serializable
data class Shows(
    val href: String? = "",
    val items: List<Item>? = listOf(),
    val limit: Int? = 0,
    val next: String? = "",
    val offset: Int? = 0,
    val previous: String? = "",
    val total: Int? = 0,
)
