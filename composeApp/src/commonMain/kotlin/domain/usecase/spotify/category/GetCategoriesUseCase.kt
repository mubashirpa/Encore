package domain.usecase.spotify.category

import core.Result
import data.mapper.toCategoriesItem
import domain.model.spotify.category.CategoriesItem
import domain.repository.SpotifyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCategoriesUseCase(private val spotifyRepository: SpotifyRepository) {
    operator fun invoke(
        accessToken: String,
        country: String? = null,
        locale: String? = null,
        limit: Int = 20,
        offset: Int = 0,
    ): Flow<Result<List<CategoriesItem>>> =
        flow {
            try {
                emit(Result.Loading())
                val categories =
                    spotifyRepository.getCategories(
                        accessToken,
                        country,
                        locale,
                        limit,
                        offset,
                    ).categories?.items?.map { it.toCategoriesItem() }
                emit(Result.Success(categories))
            } catch (e: Exception) {
                emit(Result.Error(message = e.message.toString()))
            }
        }
}
