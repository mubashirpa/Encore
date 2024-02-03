package presentation

import core.Result
import core.utils.UrlLauncher
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.usecase.spotify.RequestUserAuthorizationUseCase
import domain.usecase.spotify.accessToken.GetAccessTokenUseCase
import domain.usecase.spotify.accessToken.RefreshTokenUseCase
import domain.usecase.spotify.accessToken.RequestAuthAccessTokenUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val refreshTokenUseCase: RefreshTokenUseCase,
    private val requestAuthAccessTokenUseCase: RequestAuthAccessTokenUseCase,
    private val requestUserAuthorizationUseCase: RequestUserAuthorizationUseCase,
    private val urlLauncher: UrlLauncher,
) : ViewModel() {
    private val _isReady = MutableStateFlow(false)
    val isReady = _isReady.asStateFlow()

    // TODO("Secure variables")
    private val clientId = "08de4eaf71904d1b95254fab3015d711"
    private val clientSecret = "622b4fbad33947c59b95a6ae607de11d"
    private val redirectUrl = "blackhole://spotify/auth"
    private val scope =
        "user-read-private user-read-email playlist-read-private playlist-read-collaborative user-top-read"

    fun onEvent(event: MainUIEvent) {
        when (event) {
            is MainUIEvent.OnAuthorizationCodeReceived -> {
                requestAccessToken(event.code)
            }

            MainUIEvent.GetAccessToken -> {
                getAccessToken()
            }
        }
    }

    private fun getAccessToken() {
        viewModelScope.launch {
            val refreshToken = getAccessTokenUseCase().first().refreshToken
            if (refreshToken.isNullOrEmpty()) {
                requestUserAuthorization()
            } else {
                refreshToken(refreshToken)
            }
        }
    }

    private fun refreshToken(refreshToken: String) {
        refreshTokenUseCase(
            refreshToken = refreshToken,
            clientId = clientId,
            clientSecret = clientSecret,
        ).onEach {
            if (it is Result.Success) {
                _isReady.value = true
            }
            // TODO("Handle error")
        }.launchIn(viewModelScope)
    }

    private fun requestAccessToken(code: String) {
        requestAuthAccessTokenUseCase(
            code = code,
            redirectUri = redirectUrl,
            clientId = clientId,
            clientSecret = clientSecret,
        ).onEach {
            if (it is Result.Success) {
                _isReady.value = true
            }
            // TODO("Handle error")
        }.launchIn(viewModelScope)
    }

    private fun requestUserAuthorization() {
        val requestUserAuthorization =
            requestUserAuthorizationUseCase(
                clientId = clientId,
                redirectUri = redirectUrl,
                scope = scope,
            )
        urlLauncher.openUrl(requestUserAuthorization)
    }
}
