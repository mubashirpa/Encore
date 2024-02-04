package presentation.library

sealed class LibraryUiEvent {
    data class OnGetAccessToken(val accessToken: String) : LibraryUiEvent()
}
