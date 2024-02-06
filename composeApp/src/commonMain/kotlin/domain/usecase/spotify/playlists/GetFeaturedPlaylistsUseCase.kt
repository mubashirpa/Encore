package domain.usecase.spotify.playlists

import core.Result
import data.mapper.spotify.toPlaylist
import domain.model.playlists.Playlist
import domain.repository.SpotifyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetFeaturedPlaylistsUseCase(private val spotifyRepository: SpotifyRepository) {
    operator fun invoke(
        accessToken: String,
        country: String? = null,
        locale: String? = null,
        timestamp: String? = null,
        limit: Int = 20,
        offset: Int = 0,
    ): Flow<Result<List<Playlist>>> =
        flow {
            try {
                emit(Result.Loading())
                val playlists =
                    spotifyRepository.getFeaturedPlaylists(
                        accessToken,
                        country,
                        locale,
                        timestamp,
                        limit,
                        offset,
                    ).playlists?.items?.map { it.toPlaylist() }
                emit(Result.Success(playlists))
            } catch (e: Exception) {
                emit(Result.Error(message = e.message.toString()))
            }
        }
}
