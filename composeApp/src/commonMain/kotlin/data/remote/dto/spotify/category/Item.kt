package data.remote.dto.spotify.category

import data.remote.dto.spotify.Image
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val href: String? = null,
    val icons: List<Image>? = null,
    val id: String? = null,
    val name: String? = null
)