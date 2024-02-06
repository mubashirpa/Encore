package domain.usecase.saavn.tracks

import core.Result
import data.mapper.saavn.toSongItem
import domain.model.saavn.tracks.SongItem
import domain.repository.SaavnRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTrackUseCase(private val saavnRepository: SaavnRepository) {
    operator fun invoke(id: String): Flow<Result<SongItem>> =
        flow {
            try {
                emit(Result.Loading())
                val track = saavnRepository.getTrack(id).songs?.firstOrNull()?.toSongItem()
                emit(Result.Success(track))
            } catch (e: Exception) {
                emit(Result.Error(message = e.message.toString()))
            }
        }
}
