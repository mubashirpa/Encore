package data.mapper

import data.remote.dto.spotify.Image
import data.remote.dto.spotify.category.Item
import domain.model.spotify.category.CategoryImage
import domain.model.spotify.category.CategoryItem

fun Item.toCategoryItem(): CategoryItem {
    return CategoryItem(
        icons?.map { it.toCategoryImage() },
        id,
        name
    )
}

private fun Image.toCategoryImage(): CategoryImage {
    return CategoryImage(url)
}