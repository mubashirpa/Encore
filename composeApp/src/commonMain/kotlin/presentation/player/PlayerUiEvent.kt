package presentation.player

sealed class PlayerUiEvent {
    data class OnGetTrackId(val trackId: String) : PlayerUiEvent()
}
