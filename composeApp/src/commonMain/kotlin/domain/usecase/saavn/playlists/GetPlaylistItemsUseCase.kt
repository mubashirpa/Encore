package domain.usecase.saavn.playlists

import core.Result
import data.mapper.saavn.toPlaylistItems
import domain.model.saavn.playlists.PlaylistItems
import domain.repository.SaavnRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPlaylistItemsUseCase(private val saavnRepository: SaavnRepository) {
    operator fun invoke(playlistId: String): Flow<Result<PlaylistItems>> =
        flow {
            try {
                emit(Result.Loading())
                val playlistItems = saavnRepository.getPlaylistItems(playlistId).toPlaylistItems()
                emit(Result.Success(playlistItems))
            } catch (e: Exception) {
                emit(Result.Error(message = e.message.toString()))
            }
        }
}
