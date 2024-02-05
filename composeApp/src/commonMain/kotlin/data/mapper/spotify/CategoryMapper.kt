package data.mapper.spotify

import data.remote.dto.spotify.category.Item
import domain.model.spotify.category.CategoriesItem

fun Item.toCategoriesItem(): CategoriesItem {
    return CategoriesItem(
        icons?.firstOrNull()?.url,
        id,
        name,
    )
}
