package presentation.search

import core.Result
import domain.model.spotify.category.CategoryItem

data class SearchUiState(
    val accessToken: String = "",
    val categoriesResult: Result<List<CategoryItem>> = Result.Empty(),
)
