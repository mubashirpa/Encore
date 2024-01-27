package domain.usecase.spotify.users

import core.Result
import data.mapper.toUser
import domain.model.spotify.users.profile.User
import domain.repository.SpotifyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCurrentUsersProfileUseCase(private val spotifyRepository: SpotifyRepository) {
    operator fun invoke(accessToken: String): Flow<Result<User>> =
        flow {
            try {
                emit(Result.Loading())
                val user = spotifyRepository.getCurrentUsersProfile(accessToken).toUser()
                emit(Result.Success(user))
            } catch (e: Exception) {
                emit(Result.Error(message = e.message.toString()))
            }
        }
}
