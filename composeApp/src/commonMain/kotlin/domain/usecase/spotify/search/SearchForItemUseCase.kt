package domain.usecase.spotify.search

import core.Result
import data.mapper.toSearch
import domain.model.spotify.search.Search
import domain.repository.SearchItemType
import domain.repository.SpotifyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchForItemUseCase(private val spotifyRepository: SpotifyRepository) {
    operator fun invoke(
        accessToken: String,
        query: String,
        type: List<SearchItemType>,
        market: String? = null,
        limit: Int = 20,
        offset: Int = 0,
        includeExternal: Boolean = false,
    ): Flow<Result<Search>> =
        flow {
            try {
                emit(Result.Loading())
                val search =
                    spotifyRepository.searchForItem(
                        accessToken,
                        query,
                        type,
                        market,
                        limit,
                        offset,
                        includeExternal,
                    ).toSearch()
                emit(Result.Success(search))
            } catch (e: Exception) {
                emit(Result.Error(message = e.message.toString()))
            }
        }
}
