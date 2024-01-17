package data.remote.dto.playlists

import kotlinx.serialization.Serializable

@Serializable
data class Playlists(
    val href: String? = "",
    val items: List<Item>? = listOf(),
    val limit: Int? = 0,
    val next: String? = "",
    val offset: Int? = 0,
    val previous: String? = "",
    val total: Int? = 0
)