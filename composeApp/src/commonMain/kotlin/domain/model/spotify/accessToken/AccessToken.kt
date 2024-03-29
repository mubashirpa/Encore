package domain.model.spotify.accessToken

data class AccessToken(
    val accessToken: String? = null,
    val expiresIn: Int? = null,
    val refreshToken: String? = null,
    val scope: String? = null,
    val tokenType: String? = null,
)
