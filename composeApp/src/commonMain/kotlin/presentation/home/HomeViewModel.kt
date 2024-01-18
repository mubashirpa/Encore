package presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import core.utils.UrlLauncher
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.usecase.spotify.RequestUserAuthorizationUseCase
import domain.usecase.spotify.access_token.GetAccessTokenUseCase
import domain.usecase.spotify.access_token.RequestAuthAccessTokenUseCase
import domain.usecase.spotify.playlists.GetFeaturedPlaylistsUseCase
import domain.usecase.spotify.users.GetUsersTopTracksUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeViewModel(
    private val requestAuthAccessTokenUseCase: RequestAuthAccessTokenUseCase,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getFeaturedPlaylistsUseCase: GetFeaturedPlaylistsUseCase,
    private val getUsersTopTracksUseCase: GetUsersTopTracksUseCase,
    private val requestUserAuthorizationUseCase: RequestUserAuthorizationUseCase,
    private val urlLauncher: UrlLauncher
) : ViewModel() {

    var uiState by mutableStateOf(HomeUiState())
        private set

    init {
        getAccessToken()
    }

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.OnAuthorizationCodeReceived -> {
                requestAccessToken(event.code)
            }

            HomeUiEvent.RequestUserAuthorization -> {
                requestUserAuthorization()
            }
        }
    }

    private fun requestUserAuthorization() {
        val requestUserAuthorization = requestUserAuthorizationUseCase(
            clientId = "08de4eaf71904d1b95254fab3015d711",
            redirectUri = "blackhole://spotify/auth",
            scope = "user-read-private user-read-email playlist-read-private playlist-read-collaborative user-top-read"
        )
        urlLauncher.openUrl(requestUserAuthorization)
    }

    private fun requestAccessToken(code: String) {
        requestAuthAccessTokenUseCase(
            code = code,
            redirectUri = "blackhole://spotify/auth",
            clientId = "08de4eaf71904d1b95254fab3015d711",
            clientSecret = "622b4fbad33947c59b95a6ae607de11d"
        ).launchIn(viewModelScope)
    }

    private fun getAccessToken() {
        viewModelScope.launch {
            getAccessTokenUseCase().collectLatest {
                uiState = uiState.copy(accessToken = it.accessToken.orEmpty())
                getFeaturedPlaylists(uiState.accessToken)
                getUsersTopItems(uiState.accessToken)
            }
        }
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
            uiState = uiState.copy(usersTopTracksResult = it)
        }.launchIn(viewModelScope)
    }
}