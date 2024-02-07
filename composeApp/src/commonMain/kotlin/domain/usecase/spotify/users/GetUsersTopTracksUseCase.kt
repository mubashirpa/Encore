package domain.usecase.spotify.users

import core.Result
import data.mapper.spotify.toTrack
import domain.model.tracks.Track
import domain.repository.SpotifyRepository
import domain.repository.UsersTopItemTimeRange
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUsersTopTracksUseCase(private val spotifyRepository: SpotifyRepository) {
    operator fun invoke(
        accessToken: String,
        timeRange: UsersTopItemTimeRange = UsersTopItemTimeRange.MEDIUM_TERM,
        limit: Int = 20,
        offset: Int = 0,
    ): Flow<Result<List<Track>>> =
        flow {
            try {
                emit(Result.Loading())
                val topTracks =
                    spotifyRepository.getUsersTopTracks(
                        accessToken,
                        timeRange,
                        limit,
                        offset,
                    ).items?.map { it.toTrack() }
                emit(Result.Success(topTracks))
            } catch (e: Exception) {
                emit(Result.Error(message = e.message.toString()))
            }
        }
}
