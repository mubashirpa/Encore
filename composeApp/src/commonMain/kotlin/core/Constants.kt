package core

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object Spotify {

    const val API_BASE_URL = "https://api.spotify.com/v1"
    const val AUTHORIZE_ENDPOINT_URI = "https://accounts.spotify.com/authorize"
    const val ENDPOINT_BROWSE_CATEGORIES = "browse/categories"
    const val ENDPOINT_CURRENT_USERS_PROFILE = "me"
    const val ENDPOINT_FEATURED_PLAYLISTS = "browse/featured-playlists"
    const val ENDPOINT_USERS_TOP_ITEMS = "me/top"
    const val TOKEN_ENDPOINT_URI = "https://accounts.spotify.com/api/token"

    object Parameters {

        const val AUTHORIZATION_CODE = "authorization_code"
        const val CLIENT_CREDENTIALS = "client_credentials"
        const val CLIENT_ID = "client_id"
        const val CLIENT_SECRET = "client_secret"
        const val CODE = "code"
        const val COUNTRY = "country"
        const val GRANT_TYPE = "grant_type"
        const val LIMIT = "limit"
        const val LOCALE = "locale"
        const val OFFSET = "offset"
        const val REDIRECT_URI = "redirect_uri"
        const val REFRESH_TOKEN = "refresh_token"
        const val RESPONSE_TYPE = "response_type"
        const val SCOPE = "scope"
        const val SHOW_DIALOG = "show_dialog"
        const val STATE = "state"
        const val TIME_RANGE = "time_range"
        const val TIMESTAMP = "timestamp"
    }
}

object PreferencesKeys {

    val SPOTIFY_ACCESS_TOKEN = stringPreferencesKey("spotify_access_token")
    val SPOTIFY_TOKEN_EXPIRES_IN = intPreferencesKey("spotify_token_expires_in")
    val SPOTIFY_REFRESH_TOKEN = stringPreferencesKey("spotify_refresh_token")
    val SPOTIFY_ACCESS_TOKEN_SCOPE = stringPreferencesKey("spotify_access_token_scope")
    val SPOTIFY_TOKEN_TOKEN_TYPE = stringPreferencesKey("spotify_token_type")
}