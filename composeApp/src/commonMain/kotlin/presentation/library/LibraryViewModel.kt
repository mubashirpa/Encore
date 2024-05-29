package presentation.library

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.usecase.spotify.playlists.GetCurrentUsersPlaylistsUseCase
import domain.usecase.spotify.users.GetFollowedArtistsUseCase
import domain.usecase.spotify.users.GetUsersTopTracksUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LibraryViewModel(
    private val getCurrentUsersPlaylistsUseCase: GetCurrentUsersPlaylistsUseCase,
    private val getFollowedArtistsUseCase: GetFollowedArtistsUseCase,
    private val getUsersTopTracksUseCase: GetUsersTopTracksUseCase,
) : ViewModel() {
    var uiState by mutableStateOf(LibraryUiState())
        private set

    fun onEvent(event: LibraryUiEvent) {
        when (event) {
            is LibraryUiEvent.OnGetAccessToken -> {
                if (event.accessToken != uiState.accessToken) {
                    uiState = uiState.copy(accessToken = event.accessToken)
                    getFollowedArtists(event.accessToken)
                    getUsersPlaylists(event.accessToken)
                    getUsersTopTracks(event.accessToken)
                }
            }
        }
    }

    private fun getFollowedArtists(accessToken: String) {
        getFollowedArtistsUseCase(accessToken).onEach {
            uiState = uiState.copy(followedArtists = it)
        }.launchIn(viewModelScope)
    }

    private fun getUsersPlaylists(accessToken: String) {
        getCurrentUsersPlaylistsUseCase(accessToken).onEach {
            uiState = uiState.copy(usersPlaylists = it)
        }.launchIn(viewModelScope)
    }

    private fun getUsersTopTracks(accessToken: String) {
        getUsersTopTracksUseCase(accessToken).onEach {
            uiState = uiState.copy(usersTopTracks = it)
        }.launchIn(viewModelScope)
    }
}
