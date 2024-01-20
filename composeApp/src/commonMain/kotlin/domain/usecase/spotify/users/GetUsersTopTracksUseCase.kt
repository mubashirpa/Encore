package domain.usecase.spotify.users

import core.Result
import data.mapper.toUsersTopTrackItem
import domain.model.users.UsersTopTrackItem
import domain.repository.SpotifyRepository
import domain.repository.TimeRange
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUsersTopTracksUseCase(private val spotifyRepository: SpotifyRepository) {
    operator fun invoke(
        accessToken: String,
        timeRange: TimeRange = TimeRange.MEDIUM_TERM,
        limit: Int = 20,
        offset: Int = 0
    ): Flow<Result<List<UsersTopTrackItem>>> = flow {
        try {
            emit(Result.Loading())
            val topTracks = spotifyRepository.getUsersTopTracks(
                accessToken,
                timeRange,
                limit,
                offset
            ).items.map { it.toUsersTopTrackItem() }
            emit(Result.Success(topTracks))
        } catch (e: Exception) {
            emit(Result.Error(message = e.message.toString()))
        }
    }
}