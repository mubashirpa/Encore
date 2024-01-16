package presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import core.Result
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.usecase.spotify.access_token.GetAccessTokenUseCase
import domain.usecase.spotify.access_token.RequestAccessTokenUseCase
import domain.usecase.spotify.categories.GetCategoriesUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeViewModel(
    private val requestAccessTokenUseCase: RequestAccessTokenUseCase,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    var uiState by mutableStateOf(HomeUiState())
        private set

    init {
        getAccessToken()
    }

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.GetCategories -> {
                requestAccessToken()
            }
        }
    }

    private fun requestAccessToken() {
        requestAccessTokenUseCase().onEach {
            if (it is Result.Success) {
                val accessToken = it.data?.accessToken
                if (accessToken != null) {
                    getCategories(accessToken)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getAccessToken() {
        viewModelScope.launch {
            getAccessTokenUseCase().collectLatest {
                uiState = uiState.copy(accessToken = it.accessToken.orEmpty())
            }
        }
    }

    private fun getCategories(accessToken: String) {
        getCategoriesUseCase(accessToken).onEach {
            uiState = uiState.copy(category = it)
        }.launchIn(viewModelScope)
    }
}