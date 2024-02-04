package domain.usecase.spotify.accessToken

import core.Result
import data.mapper.toAccessToken
import domain.model.spotify.accessToken.AccessToken
import domain.repository.SpotifyRepository
import domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RequestCredentialAccessTokenUseCase(
    private val spotifyRepository: SpotifyRepository,
    private val userPreferencesRepository: UserPreferencesRepository,
) {
    operator fun invoke(
        clientId: String,
        clientSecret: String,
    ): Flow<Result<AccessToken>> =
        flow {
            try {
                emit(Result.Loading())
                val accessToken =
                    spotifyRepository.requestAccessToken(clientId, clientSecret).toAccessToken()
                userPreferencesRepository.updateAccessToken(accessToken)
                emit(Result.Success(accessToken))
            } catch (e: Exception) {
                emit(Result.Error(message = e.message.toString()))
            }
        }
}
