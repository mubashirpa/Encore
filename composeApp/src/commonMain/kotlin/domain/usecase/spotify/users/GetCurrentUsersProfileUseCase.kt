package domain.usecase.spotify.users

import core.Result
import data.mapper.spotify.toCurrentUsersProfile
import domain.model.spotify.users.CurrentUsersProfile
import domain.repository.SpotifyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCurrentUsersProfileUseCase(private val spotifyRepository: SpotifyRepository) {
    operator fun invoke(accessToken: String): Flow<Result<CurrentUsersProfile>> =
        flow {
            try {
                emit(Result.Loading())
                val user =
                    spotifyRepository.getCurrentUsersProfile(accessToken).toCurrentUsersProfile()
                emit(Result.Success(user))
            } catch (e: Exception) {
                emit(Result.Error(message = e.message.toString()))
            }
        }
}
