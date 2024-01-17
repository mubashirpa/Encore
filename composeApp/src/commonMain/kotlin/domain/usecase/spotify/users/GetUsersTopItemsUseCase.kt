package domain.usecase.spotify.users

import core.Result
import data.remote.dto.users.TopItemsDto
import domain.repository.SpotifyRepository
import domain.repository.TimeRange
import domain.repository.Type
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUsersTopItemsUseCase(private val spotifyRepository: SpotifyRepository) {
    operator fun invoke(
        accessToken: String,
        type: Type,
        timeRange: TimeRange = TimeRange.MEDIUM_TERM,
        limit: Int = 20,
        offset: Int = 0
    ): Flow<Result<TopItemsDto>> = flow {
        try {
            emit(Result.Loading())
            val playlists =
                spotifyRepository.getUsersTopItems(accessToken, type, timeRange, limit, offset)
            emit(Result.Success(playlists))
        } catch (e: Exception) {
            emit(Result.Error(message = e.message.toString()))
        }
    }
}