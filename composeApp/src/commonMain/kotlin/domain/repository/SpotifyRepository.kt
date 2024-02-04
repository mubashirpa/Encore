package domain.repository

import androidx.annotation.IntRange
import data.remote.dto.spotify.accessToken.AccessTokenDto
import data.remote.dto.spotify.category.CategoriesDto
import data.remote.dto.spotify.playlists.currentUsersPlaylists.CurrentUsersPlaylistsDto
import data.remote.dto.spotify.playlists.featuredPlaylists.FeaturedPlaylistsDto
import data.remote.dto.spotify.search.SearchDto
import data.remote.dto.spotify.users.currentUsersProfile.CurrentUsersProfileDto
import data.remote.dto.spotify.users.followedArtists.FollowedArtistsDto
import data.remote.dto.spotify.users.usersTopItems.UsersTopArtistsDto
import data.remote.dto.spotify.users.usersTopItems.UsersTopTracksDto

interface SpotifyRepository {
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
        showDialog: Boolean? = null,
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
        clientSecret: String,
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
        clientSecret: String,
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
        clientSecret: String,
    ): AccessTokenDto

    /**
     * Get a list of categories used to tag items in Spotify.
     * @param accessToken String which contains the credentials and permissions that can be used to
     * access a given resource (e.g artists, albums or tracks) or user's data.
     * @param country An ISO 3166-1 alpha-2 country code. Provide this parameter if you want to
     * narrow the list of returned categories to those relevant to a particular country. If omitted,
     * the returned items will be globally relevant.
     * @param locale The desired language, consisting of an ISO 639-1 language code and an ISO
     * 3166-1 alpha-2 country code, joined by an underscore. Provide this parameter if you want the
     * category metadata returned in a particular language.
     * @param limit The maximum number of items to return. Default: 20. Minimum: 1. Maximum: 50.
     * @param offset The index of the first item to return. Default: 0 (the first item). Use with
     * limit to get the next set of items.
     */
    suspend fun getCategories(
        accessToken: String,
        country: String? = null,
        locale: String? = null,
        limit: Int = 20,
        offset: Int = 0,
    ): CategoriesDto

    /**
     * Get a list of the playlists owned or followed by the current Spotify user.
     * @param accessToken String which contains the credentials and permissions that can be used to
     * access a given resource (e.g artists, albums or tracks) or user's data.
     * @param limit The maximum number of items to return. Default: 20. Minimum: 1. Maximum: 50.
     * @param offset The index of the first item to return. Default: 0 (the first item). Use with
     * limit to get the next set of playlists.
     */
    suspend fun getCurrentUsersPlaylists(
        accessToken: String,
        @IntRange(from = 1, to = 50) limit: Int = 20,
        offset: Int = 0,
    ): CurrentUsersPlaylistsDto

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
        offset: Int = 0,
    ): FeaturedPlaylistsDto

    /**
     * Get Spotify catalog information about albums, artists, playlists, tracks, shows, episodes or
     * audiobooks that match a keyword string.
     * @param query Your search query.
     * @param type A comma-separated list of item types to search across.
     * @param market An ISO 3166-1 alpha-2 country code. If a country code is specified, only
     * content that is available in that market will be returned.
     * @param limit The maximum number of results to return in each item type.
     * @param offset The index of the first result to return. Use with limit to get the next page of
     * search results.
     * @param includeExternal If include_external=audio is specified it signals that the client can
     * play externally hosted audio content, and marks the content as playable in the response. By
     * default externally hosted audio content is marked as unplayable in the response.
     */
    suspend fun searchForItem(
        accessToken: String,
        query: String,
        type: List<SearchItemType>,
        market: String? = null,
        limit: Int = 20,
        offset: Int = 0,
        includeExternal: Boolean = false,
    ): SearchDto

    /**
     * Get detailed profile information about the current user.
     * @param accessToken String which contains the credentials and permissions that can be used to
     * access a given resource (e.g artists, albums or tracks) or user's data.
     */
    suspend fun getCurrentUsersProfile(accessToken: String): CurrentUsersProfileDto

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
        timeRange: UsersTopItemTimeRange = UsersTopItemTimeRange.MEDIUM_TERM,
        limit: Int = 20,
        offset: Int = 0,
    ): UsersTopArtistsDto

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
        timeRange: UsersTopItemTimeRange = UsersTopItemTimeRange.MEDIUM_TERM,
        limit: Int = 20,
        offset: Int = 0,
    ): UsersTopTracksDto

    /**
     * Get the current user's followed artists.
     * @param accessToken String which contains the credentials and permissions that can be used to
     * access a given resource (e.g artists, albums or tracks) or user's data.
     * @param after The last artist ID retrieved from the previous request.
     * @param limit The maximum number of items to return. Default: 20. Minimum: 1. Maximum: 50.
     */
    suspend fun getFollowedArtists(
        accessToken: String,
        after: String? = null,
        @IntRange(from = 1, to = 50) limit: Int = 20,
    ): FollowedArtistsDto
}

@Suppress("unused")
enum class SearchItemType {
    ALBUM,
    ARTIST,
    PLAYLIST,
    TRACK,
    SHOW,
    EPISODE,
    AUDIOBOOK,
}

@Suppress("unused")
enum class UsersTopItemTimeRange {
    LONG_TERM,
    MEDIUM_TERM,
    SHORT_TERM,
}

enum class UsersTopItemType {
    ARTISTS,
    TRACKS,
}
