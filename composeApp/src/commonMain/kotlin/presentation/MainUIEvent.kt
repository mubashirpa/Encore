package presentation

sealed class MainUIEvent {
    data class OnAuthorizationCodeReceived(val code: String) : MainUIEvent()

    data object GetAccessToken : MainUIEvent()
}
