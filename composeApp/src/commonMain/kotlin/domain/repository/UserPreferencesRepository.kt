package domain.repository

import domain.model.AccessToken
import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {

    suspend fun updateAccessToken(accessToken: AccessToken)
    suspend fun getAccessToken(): Flow<AccessToken>
}