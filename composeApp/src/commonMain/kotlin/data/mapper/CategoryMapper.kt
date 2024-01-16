package data.mapper

import data.remote.dto.category.CategoryDto
import domain.model.Category

fun CategoryDto.toCategory(): Category {
    return Category(categories)
}