package presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.usecase.saavn.launchData.GetLaunchDataUseCase
import domain.usecase.spotify.playlists.GetFeaturedPlaylistsUseCase
import domain.usecase.spotify.users.GetUsersTopTracksUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeViewModel(
    private val getFeaturedPlaylistsUseCase: GetFeaturedPlaylistsUseCase,
    private val getLaunchDataUseCase: GetLaunchDataUseCase,
    private val getUsersTopTracksUseCase: GetUsersTopTracksUseCase,
) : ViewModel() {
    var uiState by mutableStateOf(HomeUiState())
        private set

    private var getFeaturedPlaylistsUseCaseJob: Job? = null
    private var getUsersTopTracksUseCaseJob: Job? = null

    init {
        getLaunchData()
    }

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.OnGetAccessToken -> {
                if (event.accessToken != uiState.accessToken) {
                    uiState = uiState.copy(accessToken = event.accessToken)
                    getFeaturedPlaylists(event.accessToken)
                    getUsersTopItems(event.accessToken)
                }
            }
        }
    }

    private fun getFeaturedPlaylists(accessToken: String) {
        getFeaturedPlaylistsUseCaseJob?.cancel()
        getFeaturedPlaylistsUseCaseJob = null
        getFeaturedPlaylistsUseCaseJob =
            getFeaturedPlaylistsUseCase(accessToken = accessToken).onEach {
                uiState = uiState.copy(featuredPlaylistsResult = it)
            }.launchIn(viewModelScope)
    }

    private fun getUsersTopItems(accessToken: String) {
        getUsersTopTracksUseCaseJob?.cancel()
        getUsersTopTracksUseCaseJob = null
        getUsersTopTracksUseCaseJob =
            getUsersTopTracksUseCase(
                accessToken = accessToken,
                limit = 6,
            ).onEach {
                uiState = uiState.copy(usersTrackItemResult = it)
            }.launchIn(viewModelScope)
    }

    private fun getLaunchData() {
        getLaunchDataUseCase(languages = listOf("english", "malayalam", "hindi", "tamil")).onEach {
            uiState = uiState.copy(launchDataResult = it)
        }.launchIn(viewModelScope)
    }
}
