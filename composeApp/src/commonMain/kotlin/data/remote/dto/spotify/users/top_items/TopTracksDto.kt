package data.remote.dto.spotify.users.top_items

import kotlinx.serialization.Serializable

@Serializable
data class TopTracksDto(
    val href: String? = null,
    val items: List<TrackItem>? = null,
    val limit: Int? = null,
    val next: String? = null,
    val offset: Int? = null,
    val previous: String? = null,
    val total: Int? = null,
)
