package domain.repository

import data.remote.dto.AccessTokenDto
import data.remote.dto.category.CategoryDto

interface SpotifyRepository {

    suspend fun requestAccessToken(): AccessTokenDto

    suspend fun getCategories(
        accessToken: String,
        country: String? = null,
        locale: String? = null,
        limit: Int = 20,
        offset: Int = 0
    ): CategoryDto
}