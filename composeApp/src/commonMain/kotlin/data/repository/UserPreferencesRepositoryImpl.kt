package data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import core.PreferencesKeys
import domain.model.spotify.AccessToken
import domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class UserPreferencesRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
) : UserPreferencesRepository {
    override suspend fun updateAccessToken(accessToken: AccessToken) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.SPOTIFY_ACCESS_TOKEN] = accessToken.accessToken ?: ""
            preferences[PreferencesKeys.SPOTIFY_TOKEN_EXPIRES_IN] = accessToken.expiresIn ?: 0
            // TODO("Make sure it is okay")
            if (!accessToken.refreshToken.isNullOrEmpty()) {
                preferences[PreferencesKeys.SPOTIFY_REFRESH_TOKEN] = accessToken.refreshToken
            }
            preferences[PreferencesKeys.SPOTIFY_ACCESS_TOKEN_SCOPE] = accessToken.scope ?: ""
            preferences[PreferencesKeys.SPOTIFY_TOKEN_TOKEN_TYPE] = accessToken.tokenType ?: ""
        }
    }

    override suspend fun getAccessToken(): Flow<AccessToken> {
        return dataStore.data
            .catch { exception ->
                // dataStore.data throws an IOException when an error is encountered when reading data
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                AccessToken(
                    accessToken = preferences[PreferencesKeys.SPOTIFY_ACCESS_TOKEN] ?: "",
                    expiresIn = preferences[PreferencesKeys.SPOTIFY_TOKEN_EXPIRES_IN] ?: 0,
                    refreshToken = preferences[PreferencesKeys.SPOTIFY_REFRESH_TOKEN] ?: "",
                    scope = preferences[PreferencesKeys.SPOTIFY_ACCESS_TOKEN_SCOPE] ?: "",
                    tokenType = preferences[PreferencesKeys.SPOTIFY_TOKEN_TOKEN_TYPE] ?: "",
                )
            }
    }
}
