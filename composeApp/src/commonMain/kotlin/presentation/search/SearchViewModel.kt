package presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import core.Result
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.repository.SearchItemType
import domain.usecase.spotify.category.GetCategoriesUseCase
import domain.usecase.spotify.search.SearchForItemUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val searchForItemUseCase: SearchForItemUseCase,
) : ViewModel() {
    var uiState by mutableStateOf(SearchUiState())
        private set

    private var getCategoriesUseCaseJob: Job? = null
    private var searchForItemUseCaseJob: Job? = null

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
                searchForItem(
                    accessToken = uiState.accessToken,
                    query = event.query,
                    type = uiState.searchItemType,
                    delay = 500,
                )
            }

            is SearchUiEvent.OnSearchItemTypeChange -> {
                uiState = uiState.copy(searchItemType = event.searchItemType)
                searchForItem(
                    accessToken = uiState.accessToken,
                    query = uiState.searchQuery,
                    type = event.searchItemType,
                )
            }

            SearchUiEvent.OnRetry -> getCategories(uiState.accessToken)
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

    private fun searchForItem(
        accessToken: String,
        query: String,
        type: SearchItemType,
        delay: Long = 0,
    ) {
        searchForItemUseCaseJob?.cancel()
        searchForItemUseCaseJob = null
        if (query.isBlank()) {
            uiState = uiState.copy(searchResult = Result.Empty())
            return
        }
        searchForItemUseCaseJob =
            viewModelScope.launch {
                delay(delay)
                searchForItemUseCase(
                    accessToken = accessToken,
                    query = query.trim(),
                    type = listOf(type),
                ).onEach {
                    uiState = uiState.copy(searchResult = it)
                }.launchIn(this)
            }
    }
}
