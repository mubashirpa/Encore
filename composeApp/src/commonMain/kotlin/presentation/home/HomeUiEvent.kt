package presentation.home

sealed class HomeUiEvent {
    data class OnAuthorizationCodeReceived(val code: String) : HomeUiEvent()
    data object RequestUserAuthorization : HomeUiEvent()
}