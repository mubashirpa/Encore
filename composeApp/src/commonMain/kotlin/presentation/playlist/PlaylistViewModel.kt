package presentation.playlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.usecase.saavn.playlists.GetPlaylistItemsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class PlaylistViewModel(
    private val getPlaylistItemsUseCase: GetPlaylistItemsUseCase,
) : ViewModel() {
    var uiState by mutableStateOf(PlaylistUiState())
        private set

    init {
        getPlaylistItems("157594140")
    }

    private fun getPlaylistItems(playlistId: String) {
        getPlaylistItemsUseCase(playlistId).onEach {
            uiState = uiState.copy(playlistItemsResult = it)
        }.launchIn(viewModelScope)
    }
}
