package domain.usecase.spotify.access_token

import core.Result
import data.mapper.toAccessToken
import domain.model.spotify.AccessToken
import domain.repository.SpotifyRepository
import domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RefreshTokenUseCase(
    private val spotifyRepository: SpotifyRepository,
    private val userPreferencesRepository: UserPreferencesRepository,
) {
    operator fun invoke(
        refreshToken: String,
        clientId: String,
        clientSecret: String,
    ): Flow<Result<AccessToken>> =
        flow {
            try {
                emit(Result.Loading())
                val accessToken =
                    spotifyRepository.refreshToken(refreshToken, clientId, clientSecret).toAccessToken()
                userPreferencesRepository.updateAccessToken(accessToken)
                emit(Result.Success(accessToken))
            } catch (e: Exception) {
                emit(Result.Error(message = e.message.toString()))
            }
        }
}
