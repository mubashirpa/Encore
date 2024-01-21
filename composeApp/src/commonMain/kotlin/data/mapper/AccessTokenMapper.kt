package data.mapper

import data.remote.dto.spotify.AccessTokenDto
import domain.model.spotify.AccessToken

fun AccessTokenDto.toAccessToken(): AccessToken {
    return AccessToken(
        accessToken,
        expiresIn,
        refreshToken,
        scope,
        tokenType
    )
}