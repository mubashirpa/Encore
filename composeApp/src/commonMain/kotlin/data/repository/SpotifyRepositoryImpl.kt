package data.repository

import core.Spotify
import data.remote.dto.AccessTokenDto
import data.remote.dto.category.CategoryDto
import domain.repository.SpotifyRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.Parameters
import io.ktor.http.appendPathSegments
import io.ktor.http.contentType

class SpotifyRepositoryImpl(private val httpClient: HttpClient) : SpotifyRepository {

    override suspend fun requestAccessToken(): AccessTokenDto {
        return httpClient.post(Spotify.TOKEN_ENDPOINT_URI) {
            contentType(ContentType.Application.FormUrlEncoded)
            setBody(
                FormDataContent(
                    Parameters.build {
                        append(Spotify.Parameters.GRANT_TYPE, Spotify.Parameters.CLIENT_CREDENTIALS)
                        append(Spotify.Parameters.CLIENT_ID, "08de4eaf71904d1b95254fab3015d711")
                        append(Spotify.Parameters.CLIENT_SECRET, "622b4fbad33947c59b95a6ae607de11d")
                    }
                )
            )
        }.body()
    }

    override suspend fun getCategories(
        accessToken: String,
        country: String?,
        locale: String?,
        limit: Int,
        offset: Int
    ): CategoryDto {
        return httpClient.get(Spotify.API_BASE_URL) {
            url {
                appendPathSegments(Spotify.ENDPOINT_BROWSE_CATEGORIES)
                if (!country.isNullOrEmpty()) {
                    parameters.append(Spotify.Parameters.COUNTRY, country)
                }
                if (!locale.isNullOrEmpty()) {
                    parameters.append(Spotify.Parameters.LOCALE, locale)
                }
                parameters.append(Spotify.Parameters.LIMIT, limit.toString())
                parameters.append(Spotify.Parameters.OFFSET, offset.toString())
            }
            header(HttpHeaders.Authorization, "Bearer $accessToken")
        }.body()
    }
}