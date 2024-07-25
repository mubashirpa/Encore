package presentation.player

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.Result
import domain.usecase.saavn.tracks.GetTrackUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class PlayerViewModel(
    private val getTrackUseCase: GetTrackUseCase,
) : ViewModel() {
    var uiState by mutableStateOf(PlayerUiState())
        private set

    fun onEvent(event: PlayerUiEvent) {
        when (event) {
            is PlayerUiEvent.OnGetTrackId -> {
                if (event.trackId != uiState.trackId) {
                    uiState = uiState.copy(trackId = event.trackId)
                    getTrack(event.trackId)
                }
            }
        }
    }

    private fun getTrack(trackId: String) {
        getTrackUseCase(trackId).onEach {
            uiState =
                if (it is Result.Success) {
                    uiState.copy(
                        trackImageUrl = it.data?.image.orEmpty(),
                        trackResult = it,
                    )
                } else {
                    uiState.copy(trackResult = it)
                }
        }.launchIn(viewModelScope)
    }
}
