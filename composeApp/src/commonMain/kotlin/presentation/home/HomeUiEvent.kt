package presentation.home

sealed class HomeUiEvent {
    data class OnGetAccessToken(val accessToken: String) : HomeUiEvent()
}