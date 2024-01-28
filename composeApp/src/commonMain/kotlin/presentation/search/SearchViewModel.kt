package presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.repository.SearchItemType
import domain.usecase.spotify.category.GetCategoriesUseCase
import domain.usecase.spotify.search.SearchForItemUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SearchViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val searchForItemUseCase: SearchForItemUseCase,
) : ViewModel() {
    var uiState by mutableStateOf(SearchUiState())
        private set

    private var getCategoriesUseCaseJob: Job? = null

    fun onEvent(event: SearchUiEvent) {
        when (event) {
            is SearchUiEvent.OnGetAccessToken -> {
                if (event.accessToken != uiState.accessToken) {
                    uiState = uiState.copy(accessToken = event.accessToken)
                    getCategories(uiState.accessToken)
                }
            }

            is SearchUiEvent.OnSearchBarActiveChange -> {
                uiState = uiState.copy(isSearchBarActive = event.isActive)
            }

            is SearchUiEvent.OnSearchBarQueryChange -> {
                uiState = uiState.copy(searchQuery = event.query)
            }

            SearchUiEvent.SearchForItem -> {
                searchForItem(uiState.accessToken)
            }
        }
    }

    private fun getCategories(accessToken: String) {
        getCategoriesUseCaseJob?.cancel()
        getCategoriesUseCaseJob = null
        getCategoriesUseCaseJob =
            getCategoriesUseCase(accessToken = accessToken).onEach {
                uiState = uiState.copy(categoriesResult = it)
            }.launchIn(viewModelScope)
    }

    private fun searchForItem(accessToken: String) {
        searchForItemUseCase(
            accessToken = accessToken,
            query = uiState.searchQuery,
            type = listOf(SearchItemType.TRACK),
        ).onEach {
            uiState = uiState.copy(searchResult = it)
        }.launchIn(viewModelScope)
    }
}
