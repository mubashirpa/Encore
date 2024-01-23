package presentation.search

sealed class SearchUiEvent {
    data class OnGetAccessToken(val accessToken: String) : SearchUiEvent()
}