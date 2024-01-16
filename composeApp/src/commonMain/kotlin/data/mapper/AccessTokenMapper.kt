package data.mapper

import data.remote.dto.AccessTokenDto
import domain.model.AccessToken

fun AccessTokenDto.toAccessToken(): AccessToken {
    return AccessToken(accessToken, expiresIn, tokenType)
}