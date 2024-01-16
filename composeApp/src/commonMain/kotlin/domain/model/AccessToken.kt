package domain.model

data class AccessToken(
    val accessToken: String? = null,
    val expiresIn: Int? = null,
    val tokenType: String? = null
)