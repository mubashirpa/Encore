package data.remote.dto.users

import kotlinx.serialization.Serializable

@Serializable
data class TopTracksDto(
    val href: String = "",
    val items: List<TrackItem> = listOf(),
    val limit: Int = 0,
    val next: String? = "",
    val offset: Int = 0,
    val previous: String? = "",
    val total: Int? = 0
)