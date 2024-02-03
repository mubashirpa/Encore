package data.mapper

import data.remote.dto.spotify.category.Item
import domain.model.spotify.category.Category

fun Item.toCategoryItem(): Category {
    return Category(
        icons?.firstOrNull()?.url,
        id,
        name,
    )
}
