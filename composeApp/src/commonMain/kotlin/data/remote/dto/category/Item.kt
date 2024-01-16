package data.remote.dto.category

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val href: String? = null,
    val icons: List<Icon?>? = null,
    val id: String? = null,
    val name: String? = null
)