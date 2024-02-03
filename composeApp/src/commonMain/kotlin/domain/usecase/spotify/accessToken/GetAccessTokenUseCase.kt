package domain.usecase.spotify.accessToken

import domain.model.spotify.AccessToken
import domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow

class GetAccessTokenUseCase(
    private val userPreferencesRepository: UserPreferencesRepository,
) {
    suspend operator fun invoke(): Flow<AccessToken> = userPreferencesRepository.getAccessToken()
}
