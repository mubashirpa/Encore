package data.mapper

import data.remote.dto.spotify.accessToken.AccessTokenDto
import domain.model.spotify.accessToken.AccessToken

fun AccessTokenDto.toAccessToken(): AccessToken {
    return AccessToken(
        accessToken,
        expiresIn,
        refreshToken,
        scope,
        tokenType,
    )
}
