package presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import core.Result
import core.utils.UrlLauncher
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.repository.Type
import domain.usecase.spotify.RequestUserAuthorizationUseCase
import domain.usecase.spotify.access_token.GetAccessTokenUseCase
import domain.usecase.spotify.access_token.RequestAccessTokenUseCase
import domain.usecase.spotify.categories.GetCategoriesUseCase
import domain.usecase.spotify.playlists.GetFeaturedPlaylistsUseCase
import domain.usecase.spotify.users.GetUsersTopItemsUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeViewModel(
    private val requestAccessTokenUseCase: RequestAccessTokenUseCase,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getFeaturedPlaylistsUseCase: GetFeaturedPlaylistsUseCase,
    private val getUsersTopItemsUseCase: GetUsersTopItemsUseCase,
    private val requestUserAuthorizationUseCase: RequestUserAuthorizationUseCase,
    private val urlLauncher: UrlLauncher
) : ViewModel() {

    var uiState by mutableStateOf(HomeUiState())
        private set

//    init {
//        getAccessToken()
//    }

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.GetCategories -> {
//                requestAccessToken()
                requestUserAuthorization()
            }
        }
    }

    private fun requestUserAuthorization() {
        val requestUserAuthorization = requestUserAuthorizationUseCase(
            clientId = "08de4eaf71904d1b95254fab3015d711",
            redirectUri = "blackhole://spotify/auth",
            scope = "user-read-private user-read-email playlist-read-private playlist-read-collaborative"
        )
        urlLauncher.openUrl(requestUserAuthorization)
    }

    private fun requestAccessToken() {
        requestAccessTokenUseCase().onEach {
            if (it is Result.Success) {
                val accessToken = it.data?.accessToken
                if (accessToken != null) {
                    getFeaturedPlaylists(accessToken)
                    getUsersTopItems(accessToken)
                }
            }
        }.launchIn(viewModelScope)
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

    private fun getCategories(accessToken: String) {
        getCategoriesUseCase(accessToken).onEach {
            uiState = uiState.copy(category = it)
        }.launchIn(viewModelScope)
    }

    private fun getFeaturedPlaylists(accessToken: String) {
        getFeaturedPlaylistsUseCase(accessToken).onEach {
            uiState = uiState.copy(featuredPlaylistsResult = it)
        }.launchIn(viewModelScope)
    }

    private fun getUsersTopItems(accessToken: String) {
        getUsersTopItemsUseCase(accessToken = accessToken, type = Type.TRACKS).onEach {
            when (it) {
                is Result.Empty -> println("hello: Empty")
                is Result.Error -> println("hello: Error, ${it.message}")
                is Result.Loading -> println("hello: Loading")
                is Result.Success -> println("hello: ${it.data}")
            }
        }.launchIn(viewModelScope)
    }
}