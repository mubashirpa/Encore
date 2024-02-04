package data.remote.dto.spotify.users.followedArtists

import kotlinx.serialization.Serializable

@Serializable
data class Artists(
    val cursors: Cursors? = null,
    val href: String? = null,
    val items: List<Item>? = null,
    val limit: Int? = null,
    val next: String? = null,
    val total: Int? = null,
)
