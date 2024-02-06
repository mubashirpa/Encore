package domain.usecase.saavn.playlists

import core.Result
import data.mapper.saavn.toPlaylist
import domain.model.playlists.Playlist
import domain.repository.SaavnRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPlaylistItemsUseCase(private val saavnRepository: SaavnRepository) {
    operator fun invoke(playlistId: String): Flow<Result<Playlist>> =
        flow {
            try {
                emit(Result.Loading())
                val playlist = saavnRepository.getPlaylistItems(playlistId).toPlaylist()
                emit(Result.Success(playlist))
            } catch (e: Exception) {
                emit(Result.Error(message = e.message.toString()))
            }
        }
}
