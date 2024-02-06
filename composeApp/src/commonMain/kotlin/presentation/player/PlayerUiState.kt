package presentation.player

import core.Result
import domain.model.saavn.tracks.SongItem

data class PlayerUiState(
    val trackId: String = "",
    val trackResult: Result<SongItem> = Result.Empty(),
)
