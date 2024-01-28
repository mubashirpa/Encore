package presentation.search

import core.Result
import data.remote.dto.spotify.search.SearchDto
import domain.model.spotify.category.CategoryItem

data class SearchUiState(
    val accessToken: String = "",
    val categoriesResult: Result<List<CategoryItem>> = Result.Empty(),
    val isSearchBarActive: Boolean = false,
    val searchResult: Result<SearchDto> = Result.Empty(),
    val searchQuery: String = "",
)
