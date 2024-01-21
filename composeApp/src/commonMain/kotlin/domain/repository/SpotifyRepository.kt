package domain.repository

import data.remote.dto.spotify.AccessTokenDto
import data.remote.dto.spotify.category.CategoriesDto
import data.remote.dto.spotify.playlists.PlaylistsDto
import data.remote.dto.spotify.users.top_items.TopTracksDto
import data.remote.dto.spotify.users.profile.UserDto

interface SpotifyRepository {

    /**
     * Get detailed profile information about the current user.
     * @param accessToken String which contains the credentials and permissions that can be used to
     * access a given resource (e.g artists, albums or tracks) or user's data.
     */
    suspend fun getCurrentUsersProfile(accessToken: String): UserDto

    /**
     * Request authorization from the user so that our app can access to the Spotify resources on the user's behalf.
     * @param clientId The Client ID generated after registering your application.
     * @param responseType Set to code.
     * @param redirectUri The URI to redirect to after the user grants or denies permission. This
     * URI needs to have been entered in the Redirect URI allowlist that you specified when you
     * registered your application. The value of redirect_uri here must exactly
     * match one of the values you entered when you registered your application, including upper or
     * lowercase, terminating slashes, and such.
     * @param state This provides protection against attacks such as cross-site request forgery.
     * @param scope A space-separated list of scopes.If no scopes are specified, authorization
     * will be granted only to access publicly available information: that is, only information
     * normally visible in the Spotify desktop, web, and mobile players.
     * @param showDialog Whether or not to force the user to approve the app again if they’ve
     * already done so. If false (default), a user who has already approved the application may be
     * automatically redirected to the URI specified by redirect_uri. If true, the user will not be
     * automatically redirected and will have to approve the app again.
     */
    fun requestUserAuthorization(
        clientId: String,
        responseType: String = "code",
        redirectUri: String,
        state: String? = null,
        scope: String? = null,
        showDialog: Boolean? = null
    ): String

    /**
     * The access token is a string which contains the credentials and permissions that can be used
     * to access a given resource (e.g artists, albums or tracks) or user's data (e.g your profile
     * or your playlists).
     * @param clientId The Client ID generated after registering your application.
     * @param clientSecret The Client Secret generated after registering your application.
     */
    suspend fun requestAccessToken(
        clientId: String,
        clientSecret: String
    ): AccessTokenDto

    /**
     * The access token is a string which contains the credentials and permissions that can be used
     * to access a given resource (e.g artists, albums or tracks) or user's data (e.g your profile
     * or your playlists).
     * @param code The authorization code returned from the UserAuthorization request.
     * @param redirectUri This parameter is used for validation only (there is no actual
     * redirection). The value of this parameter must exactly match the value of redirect_uri
     * supplied when requesting the authorization code.
     * @param clientId The Client ID generated after registering your application.
     * @param clientSecret The Client Secret generated after registering your application.
     */
    suspend fun requestAccessToken(
        code: String,
        redirectUri: String,
        clientId: String,
        clientSecret: String
    ): AccessTokenDto

    /**
     * A refresh token is a security credential that allows client applications to obtain new access
     * tokens without requiring users to reauthorize the application.
     * @param refreshToken The refresh token returned from the authorization token request.
     * @param clientId The client ID for your app, available from the developer dashboard.
     * @param clientSecret The client secret for your app, available from the developer dashboard.
     */
    suspend fun refreshToken(
        refreshToken: String,
        clientId: String,
        clientSecret: String
    ): AccessTokenDto

    /**
     * Get a list of Spotify featured playlists (shown, for example, on a Spotify player's 'Browse'
     * tab).
     * @param accessToken String which contains the credentials and permissions that can be used to
     * access a given resource (e.g artists, albums or tracks) or user's data.
     * @param country An ISO 3166-1 alpha-2 country code. Provide this parameter if you want the
     * list of returned items to be relevant to a particular country. If omitted, the returned items
     * will be relevant to all countries.
     * @param locale The desired language, consisting of a lowercase ISO 639-1 language code and an
     * uppercase ISO 3166-1 alpha-2 country code, joined by an underscore. Provide this parameter if
     * you want the results returned in a particular language (where available).
     * @param timestamp A timestamp in ISO 8601 format: yyyy-MM-ddTHH:mm:ss. Use this parameter to
     * specify the user's local time to get results tailored for that specific date and time in the
     * day. If not provided, the response defaults to the current UTC time.
     * Example: "2014-10-23T09:00:00" for a user whose local time is 9AM. If there were no featured
     * playlists (or there is no data) at the specified time, the response will revert to the
     * current UTC time.
     * @param limit The maximum number of items to return. Default: 20. Minimum: 1. Maximum: 50.
     * @param offset The index of the first item to return. Default: 0 (the first item). Use with
     * limit to get the next set of items.
     */
    suspend fun getFeaturedPlaylists(
        accessToken: String,
        country: String? = null,
        locale: String? = null,
        timestamp: String? = null,
        limit: Int = 20,
        offset: Int = 0
    ): PlaylistsDto

    /**
     * Get the current user's top artists based on calculated affinity.
     * @param accessToken String which contains the credentials and permissions that can be used to
     * access a given resource (e.g artists, albums or tracks) or user's data.
     * @param timeRange Over what time frame the affinities are computed.
     * @param limit The maximum number of items to return. Default: 20. Minimum: 1. Maximum: 50.
     * @param offset The index of the first item to return. Default: 0 (the first item). Use with
     * limit to get the next set of items.
     */
    suspend fun getUsersTopArtists(
        accessToken: String,
        timeRange: TimeRange = TimeRange.MEDIUM_TERM,
        limit: Int = 20,
        offset: Int = 0
    ): TopTracksDto

    /**
     * Get the current user's top tracks based on calculated affinity.
     * @param accessToken String which contains the credentials and permissions that can be used to
     * access a given resource (e.g artists, albums or tracks) or user's data.
     * @param timeRange Over what time frame the affinities are computed.
     * @param limit The maximum number of items to return. Default: 20. Minimum: 1. Maximum: 50.
     * @param offset The index of the first item to return. Default: 0 (the first item). Use with
     * limit to get the next set of items.
     */
    suspend fun getUsersTopTracks(
        accessToken: String,
        timeRange: TimeRange = TimeRange.MEDIUM_TERM,
        limit: Int = 20,
        offset: Int = 0
    ): TopTracksDto

    suspend fun getCategories(
        accessToken: String,
        country: String? = null,
        locale: String? = null,
        limit: Int = 20,
        offset: Int = 0
    ): CategoriesDto
}

enum class Type {
    ARTISTS,
    TRACKS
}

@Suppress("unused")
enum class TimeRange {
    LONG_TERM,
    MEDIUM_TERM,
    SHORT_TERM
}