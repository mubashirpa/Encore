package domain.usecase.saavn.tracks

import core.Result
import data.mapper.saavn.toTrack
import domain.model.tracks.Track
import domain.repository.SaavnRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTrackUseCase(private val saavnRepository: SaavnRepository) {
    operator fun invoke(id: String): Flow<Result<Track>> =
        flow {
            try {
                emit(Result.Loading())
                val track = saavnRepository.getTrack(id).songs?.firstOrNull()?.toTrack()
                emit(Result.Success(track))
            } catch (e: Exception) {
                emit(Result.Error(message = e.message.toString()))
            }
        }
}
