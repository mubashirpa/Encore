package presentation.search

import domain.repository.SearchItemType

sealed class SearchUiEvent {
    data class OnGetAccessToken(val accessToken: String) : SearchUiEvent()

    data class OnSearchBarActiveChange(val isActive: Boolean) : SearchUiEvent()

    data class OnSearchBarQueryChange(val query: String) : SearchUiEvent()

    data class OnSearchItemTypeChange(val searchItemType: SearchItemType) : SearchUiEvent()
}
