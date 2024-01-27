package data.remote.dto.spotify.search.artists

import kotlinx.serialization.Serializable

@Serializable
data class Artists(
    val href: String? = null,
    val items: List<Item>? = null,
    val limit: Int? = null,
    val next: String? = null,
    val offset: Int? = null,
    val previous: String? = null,
    val total: Int? = null,
)
