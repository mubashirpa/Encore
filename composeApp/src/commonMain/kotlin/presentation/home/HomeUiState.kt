package presentation.home

import core.Result
import domain.model.Category

data class HomeUiState(
    val accessToken: String = "",
    val category: Result<Category> = Result.Empty()
)