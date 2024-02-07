package data.mapper.spotify

import data.remote.dto.spotify.categories.Item
import domain.model.spotify.categories.CategoriesItem

fun Item.toCategoriesItem(): CategoriesItem {
    return CategoriesItem(
        icons?.firstOrNull()?.url,
        id,
        name,
    )
}
