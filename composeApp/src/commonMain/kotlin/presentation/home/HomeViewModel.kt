package presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import core.Result
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.usecase.spotify.access_token.GetAccessTokenUseCase
import domain.usecase.spotify.playlists.GetFeaturedPlaylistsUseCase
import domain.usecase.spotify.users.GetCurrentUsersProfileUseCase
import domain.usecase.spotify.users.GetUsersTopTracksUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getCurrentUsersProfileUseCase: GetCurrentUsersProfileUseCase,
    private val getFeaturedPlaylistsUseCase: GetFeaturedPlaylistsUseCase,
    private val getUsersTopTracksUseCase: GetUsersTopTracksUseCase
) : ViewModel() {

    var uiState by mutableStateOf(HomeUiState())
        private set

    init {
        getAccessToken()
    }

    private fun getAccessToken() {
        viewModelScope.launch {
            getAccessTokenUseCase().collectLatest {
                val accessToken = it.accessToken
                if (!accessToken.isNullOrEmpty()) {
                    uiState = uiState.copy(accessToken = accessToken)
                    getCurrentUsersProfile(uiState.accessToken)
                    getFeaturedPlaylists(uiState.accessToken)
                    getUsersTopItems(uiState.accessToken)
                }
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

    private fun getFeaturedPlaylists(accessToken: String) {
        getFeaturedPlaylistsUseCase(accessToken = accessToken).onEach {
            uiState = uiState.copy(featuredPlaylistsResult = it)
        }.launchIn(viewModelScope)
    }

    private fun getUsersTopItems(accessToken: String) {
        getUsersTopTracksUseCase(
            accessToken = accessToken,
            limit = 6
        ).onEach {
            uiState = uiState.copy(usersTrackItemResult = it)
        }.launchIn(viewModelScope)
    }
}