package presentation.playlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.Result
import domain.usecase.saavn.playlists.GetPlaylistItemsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class PlaylistViewModel(
    private val getPlaylistItemsUseCase: GetPlaylistItemsUseCase,
) : ViewModel() {
    var uiState by mutableStateOf(PlaylistUiState())
        private set

    fun onEvent(event: PlaylistUiEvent) {
        when (event) {
            is PlaylistUiEvent.OnGetPlaylistId -> {
                if (event.playlistId != uiState.playlistId) {
                    uiState = uiState.copy(playlistId = event.playlistId)
                    getPlaylistItems(event.playlistId)
                }
            }

            PlaylistUiEvent.OnRetry -> {
                getPlaylistItems(uiState.playlistId)
            }
        }
    }

    private fun getPlaylistItems(playlistId: String) {
        getPlaylistItemsUseCase(playlistId).onEach {
            uiState =
                if (it is Result.Success) {
                    uiState.copy(
                        playlistImageUrl = it.data?.image.orEmpty(),
                        playlistItemsResult = it,
                    )
                } else {
                    uiState.copy(playlistItemsResult = it)
                }
        }.launchIn(viewModelScope)
    }
}
