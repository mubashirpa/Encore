package data.remote.dto.spotify.users.top_items

import kotlinx.serialization.Serializable

@Serializable
data class TopArtistsDto(
    val href: String? = null,
    val items: List<ArtistItem>? = null,
    val limit: Int? = null,
    val next: String? = null,
    val offset: Int? = null,
    val previous: String? = null,
    val total: Int? = null
)