package domain.usecase.spotify

import domain.repository.SpotifyRepository

class RequestUserAuthorizationUseCase(
    private val spotifyRepository: SpotifyRepository
) {
    operator fun invoke(
        clientId: String,
        responseType: String = "code",
        redirectUri: String,
        state: String? = null,
        scope: String? = null,
        showDialog: Boolean? = null
    ): String = spotifyRepository.requestUserAuthorization(
        clientId,
        responseType,
        redirectUri,
        state,
        scope,
        showDialog
    )
}