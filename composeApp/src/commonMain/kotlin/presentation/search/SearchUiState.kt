package presentation.search

import core.Result
import domain.model.spotify.category.Category
import domain.model.spotify.search.Search
import domain.repository.SearchItemType

data class SearchUiState(
    val accessToken: String = "",
    val categoriesResult: Result<List<Category>> = Result.Empty(),
    val isSearchBarActive: Boolean = false,
    val searchItemType: SearchItemType = SearchItemType.TRACK,
    val searchResult: Result<Search> = Result.Empty(),
    val searchQuery: String = "",
)
