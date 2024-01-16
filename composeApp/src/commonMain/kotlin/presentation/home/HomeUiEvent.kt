package presentation.home

sealed class HomeUiEvent {
    data object GetCategories : HomeUiEvent()
}