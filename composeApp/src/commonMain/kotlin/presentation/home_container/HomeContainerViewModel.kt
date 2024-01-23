package presentation.home_container

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import core.Result
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.usecase.spotify.access_token.GetAccessTokenUseCase
import domain.usecase.spotify.users.GetCurrentUsersProfileUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeContainerViewModel(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getCurrentUsersProfileUseCase: GetCurrentUsersProfileUseCase,
) : ViewModel() {

    var uiState by mutableStateOf(HomeContainerUiState())
        private set

    init {
        getAccessToken()
    }

    private fun getAccessToken() {
        viewModelScope.launch {
            val accessToken = getAccessTokenUseCase().first().accessToken
            if (!accessToken.isNullOrEmpty()) {
                uiState = uiState.copy(accessToken = accessToken)
                getCurrentUsersProfile(uiState.accessToken)
            }
        }
    }

    private fun getCurrentUsersProfile(accessToken: String) {
        getCurrentUsersProfileUseCase(accessToken).onEach {
            if (it is Result.Success) {
                uiState = uiState.copy(currentUsersProfile = it.data)
            }
        }.launchIn(viewModelScope)
    }
}