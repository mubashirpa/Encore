package domain.usecase.spotify.categories

import data.mapper.toCategory
import core.Result
import domain.model.Category
import domain.repository.SpotifyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCategoriesUseCase(private val spotifyRepository: SpotifyRepository) {
    operator fun invoke(
        accessToken: String,
        country: String? = null,
        locale: String? = null,
        limit: Int = 20,
        offset: Int = 0
    ): Flow<Result<Category>> = flow {
        try {
            emit(Result.Loading())
            val category =
                spotifyRepository.getCategories(accessToken, country, locale, limit, offset)
                    .toCategory()
            emit(Result.Success(category))
        } catch (e: Exception) {
            emit(Result.Error(message = e.message.toString()))
        }
    }
}