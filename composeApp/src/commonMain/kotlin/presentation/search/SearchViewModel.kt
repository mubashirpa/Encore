package presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.usecase.spotify.category.GetCategoriesUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SearchViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
) : ViewModel() {
    var uiState by mutableStateOf(SearchUiState())
        private set

    fun onEvent(event: SearchUiEvent) {
        when (event) {
            is SearchUiEvent.OnGetAccessToken -> {
                if (event.accessToken != uiState.accessToken) {
                    uiState = uiState.copy(accessToken = event.accessToken)
                    getCategories(uiState.accessToken)
                }
            }
        }
    }

    private fun getCategories(accessToken: String) {
        getCategoriesUseCase(accessToken = accessToken).onEach {
            uiState = uiState.copy(categoriesResult = it)
        }.launchIn(viewModelScope)
    }
}
