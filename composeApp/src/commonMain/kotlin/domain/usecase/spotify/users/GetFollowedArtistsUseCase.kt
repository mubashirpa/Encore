package domain.usecase.spotify.users

import core.Result
import data.mapper.spotify.toFollowedArtistsItem
import domain.model.spotify.users.followedArtists.FollowedArtistsItem
import domain.repository.SpotifyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetFollowedArtistsUseCase(private val spotifyRepository: SpotifyRepository) {
    operator fun invoke(
        accessToken: String,
        after: String? = null,
        limit: Int = 20,
    ): Flow<Result<List<FollowedArtistsItem>>> =
        flow {
            try {
                emit(Result.Loading())
                val followedArtists =
                    spotifyRepository.getFollowedArtists(
                        accessToken,
                        after,
                        limit,
                    ).artists?.items?.map { it.toFollowedArtistsItem() }
                emit(Result.Success(followedArtists))
            } catch (e: Exception) {
                emit(Result.Error(message = e.message.toString()))
            }
        }
}
