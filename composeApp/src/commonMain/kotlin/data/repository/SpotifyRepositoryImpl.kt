package data.repository

import core.Spotify
import data.remote.dto.spotify.accessToken.AccessTokenDto
import data.remote.dto.spotify.category.CategoriesDto
import data.remote.dto.spotify.playlists.currentUsersPlaylists.CurrentUsersPlaylistsDto
import data.remote.dto.spotify.playlists.featuredPlaylists.FeaturedPlaylistsDto
import data.remote.dto.spotify.search.SearchDto
import data.remote.dto.spotify.users.currentUsersProfile.CurrentUsersProfileDto
import data.remote.dto.spotify.users.followedArtists.FollowedArtistsDto
import data.remote.dto.spotify.users.usersTopItems.UsersTopArtistsDto
import data.remote.dto.spotify.users.usersTopItems.UsersTopTracksDto
import domain.repository.SearchItemType
import domain.repository.SpotifyRepository
import domain.repository.UsersTopItemTimeRange
import domain.repository.UsersTopItemType
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
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
import io.ktor.util.encodeBase64

class SpotifyRepositoryImpl(
    private val httpClient: HttpClient,
) : SpotifyRepository {
    override fun requestUserAuthorization(
        clientId: String,
        responseType: String,
        redirectUri: String,
        state: String?,
        scope: String?,
        showDialog: Boolean?,
    ): String {
        val authorizationUrl = StringBuilder(Spotify.AUTHORIZE_ENDPOINT_URI)
        authorizationUrl.append("?${Spotify.Parameters.CLIENT_ID}=$clientId")
        authorizationUrl.append("&${Spotify.Parameters.RESPONSE_TYPE}=$responseType")
        authorizationUrl.append("&${Spotify.Parameters.REDIRECT_URI}=$redirectUri")
        if (!state.isNullOrEmpty()) {
            authorizationUrl.append("&${Spotify.Parameters.STATE}=$state")
        }
        if (!scope.isNullOrEmpty()) {
            authorizationUrl.append("&${Spotify.Parameters.SCOPE}=${scope.replace(" ", "%20")}")
        }
        if (showDialog != null) {
            authorizationUrl.append("&${Spotify.Parameters.SHOW_DIALOG}=$showDialog")
        }
        return authorizationUrl.toString()
    }

    override suspend fun requestAccessToken(
        clientId: String,
        clientSecret: String,
    ): AccessTokenDto {
        return httpClient.post(Spotify.TOKEN_ENDPOINT_URI) {
            contentType(ContentType.Application.FormUrlEncoded)
            setBody(
                FormDataContent(
                    Parameters.build {
                        append(Spotify.Parameters.GRANT_TYPE, Spotify.Parameters.CLIENT_CREDENTIALS)
                        append(Spotify.Parameters.CLIENT_ID, clientId)
                        append(Spotify.Parameters.CLIENT_SECRET, clientSecret)
                    },
                ),
            )
        }.body()
    }

    override suspend fun requestAccessToken(
        code: String,
        redirectUri: String,
        clientId: String,
        clientSecret: String,
    ): AccessTokenDto {
        val credentials = "$clientId:$clientSecret".encodeBase64()
        return httpClient.post(Spotify.TOKEN_ENDPOINT_URI) {
            setBody(
                FormDataContent(
                    Parameters.build {
                        append(Spotify.Parameters.GRANT_TYPE, Spotify.Parameters.AUTHORIZATION_CODE)
                        append(Spotify.Parameters.CODE, code)
                        append(Spotify.Parameters.REDIRECT_URI, redirectUri)
                    },
                ),
            )
            header(HttpHeaders.Authorization, "Basic $credentials")
            contentType(ContentType.Application.FormUrlEncoded)
        }.body()
    }

    override suspend fun refreshToken(
        refreshToken: String,
        clientId: String,
        clientSecret: String,
    ): AccessTokenDto {
        val credentials = "$clientId:$clientSecret".encodeBase64()
        return httpClient.post(Spotify.TOKEN_ENDPOINT_URI) {
            setBody(
                FormDataContent(
                    Parameters.build {
                        append(Spotify.Parameters.GRANT_TYPE, Spotify.Parameters.REFRESH_TOKEN)
                        append(Spotify.Parameters.REFRESH_TOKEN, refreshToken)
                    },
                ),
            )
            contentType(ContentType.Application.FormUrlEncoded)
            header(HttpHeaders.Authorization, "Basic $credentials")
        }.body()
    }

    override suspend fun getCategories(
        accessToken: String,
        country: String?,
        locale: String?,
        limit: Int,
        offset: Int,
    ): CategoriesDto {
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
            authorisationHeader(accessToken)
        }.body()
    }

    override suspend fun getCurrentUsersPlaylists(
        accessToken: String,
        limit: Int,
        offset: Int,
    ): CurrentUsersPlaylistsDto {
        return httpClient.get(Spotify.API_BASE_URL) {
            url {
                appendPathSegments(Spotify.ENDPOINT_CURRENT_USERS_PLAYLISTS)
                parameters.append(Spotify.Parameters.LIMIT, limit.toString())
                parameters.append(Spotify.Parameters.OFFSET, offset.toString())
            }
            authorisationHeader(accessToken)
        }.body()
    }

    override suspend fun getFeaturedPlaylists(
        accessToken: String,
        country: String?,
        locale: String?,
        timestamp: String?,
        limit: Int,
        offset: Int,
    ): FeaturedPlaylistsDto {
        return httpClient.get(Spotify.API_BASE_URL) {
            url {
                appendPathSegments(Spotify.ENDPOINT_FEATURED_PLAYLISTS)
                if (!country.isNullOrEmpty()) {
                    parameters.append(Spotify.Parameters.COUNTRY, country)
                }
                if (!locale.isNullOrEmpty()) {
                    parameters.append(Spotify.Parameters.LOCALE, locale)
                }
                if (!timestamp.isNullOrEmpty()) {
                    parameters.append(Spotify.Parameters.TIMESTAMP, timestamp)
                }
                parameters.append(Spotify.Parameters.LIMIT, limit.toString())
                parameters.append(Spotify.Parameters.OFFSET, offset.toString())
            }
            authorisationHeader(accessToken)
        }.body()
    }

    override suspend fun searchForItem(
        accessToken: String,
        query: String,
        type: List<SearchItemType>,
        market: String?,
        limit: Int,
        offset: Int,
        includeExternal: Boolean,
    ): SearchDto {
        return httpClient.get(Spotify.API_BASE_URL) {
            url {
                appendPathSegments(Spotify.ENDPOINT_SEARCH)
                parameters.append(Spotify.Parameters.Q, query)
                parameters.append(
                    Spotify.Parameters.TYPE,
                    type.joinToString("%2C") { it.name.lowercase() },
                )
                if (!market.isNullOrEmpty()) {
                    parameters.append(Spotify.Parameters.MARKET, market)
                }
                parameters.append(Spotify.Parameters.LIMIT, limit.toString())
                parameters.append(Spotify.Parameters.OFFSET, offset.toString())
                if (includeExternal) {
                    parameters.append(Spotify.Parameters.INCLUDE_EXTERNAL, Spotify.Parameters.AUDIO)
                }
            }
            authorisationHeader(accessToken)
        }.body()
    }

    override suspend fun getCurrentUsersProfile(accessToken: String): CurrentUsersProfileDto {
        return httpClient.get(Spotify.API_BASE_URL) {
            url {
                appendPathSegments(Spotify.ENDPOINT_CURRENT_USERS_PROFILE)
            }
            authorisationHeader(accessToken)
        }.body()
    }

    override suspend fun getUsersTopArtists(
        accessToken: String,
        timeRange: UsersTopItemTimeRange,
        limit: Int,
        offset: Int,
    ): UsersTopArtistsDto {
        return httpClient.get(Spotify.API_BASE_URL) {
            url {
                appendPathSegments(Spotify.ENDPOINT_USERS_TOP_ITEMS)
                appendPathSegments(UsersTopItemType.ARTISTS.name.lowercase())
                parameters.append(Spotify.Parameters.TIME_RANGE, timeRange.name.lowercase())
                parameters.append(Spotify.Parameters.LIMIT, limit.toString())
                parameters.append(Spotify.Parameters.OFFSET, offset.toString())
            }
            authorisationHeader(accessToken)
        }.body()
    }

    override suspend fun getUsersTopTracks(
        accessToken: String,
        timeRange: UsersTopItemTimeRange,
        limit: Int,
        offset: Int,
    ): UsersTopTracksDto {
        return httpClient.get(Spotify.API_BASE_URL) {
            url {
                appendPathSegments(Spotify.ENDPOINT_USERS_TOP_ITEMS)
                appendPathSegments(UsersTopItemType.TRACKS.name.lowercase())
                parameters.append(Spotify.Parameters.TIME_RANGE, timeRange.name.lowercase())
                parameters.append(Spotify.Parameters.LIMIT, limit.toString())
                parameters.append(Spotify.Parameters.OFFSET, offset.toString())
            }
            authorisationHeader(accessToken)
        }.body()
    }

    override suspend fun getFollowedArtists(
        accessToken: String,
        after: String?,
        limit: Int,
    ): FollowedArtistsDto {
        return httpClient.get(Spotify.API_BASE_URL) {
            url {
                appendPathSegments(Spotify.ENDPOINT_FOLLOWED_ARTISTS)
                parameters.append(Spotify.Parameters.TYPE, Spotify.Parameters.ARTIST)
                if (!after.isNullOrEmpty()) {
                    parameters.append(Spotify.Parameters.AFTER, after)
                }
                parameters.append(Spotify.Parameters.LIMIT, limit.toString())
            }
            authorisationHeader(accessToken)
        }.body()
    }

    private fun HttpRequestBuilder.authorisationHeader(accessToken: String) {
        return header(HttpHeaders.Authorization, "Bearer $accessToken")
    }
}
