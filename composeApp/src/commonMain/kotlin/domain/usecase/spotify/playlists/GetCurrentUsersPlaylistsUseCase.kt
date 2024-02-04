package domain.usecase.spotify.playlists

import core.Result
import data.mapper.toCurrentUsersPlaylistsItem
import domain.model.spotify.playlists.PlaylistsItem
import domain.repository.SpotifyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCurrentUsersPlaylistsUseCase(private val spotifyRepository: SpotifyRepository) {
    operator fun invoke(
        accessToken: String,
        limit: Int = 20,
        offset: Int = 0,
    ): Flow<Result<List<PlaylistsItem>>> =
        flow {
            try {
                emit(Result.Loading())
                val playlists =
                    spotifyRepository.getCurrentUsersPlaylists(
                        accessToken,
                        limit,
                        offset,
                    ).items?.map { it.toCurrentUsersPlaylistsItem() }
                emit(Result.Success(playlists))
            } catch (e: Exception) {
                emit(Result.Error(message = e.message.toString()))
            }
        }
}
