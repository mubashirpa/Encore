package presentation.player

import core.Result
import domain.model.tracks.Track

data class PlayerUiState(
    val trackId: String = "",
    val trackResult: Result<Track> = Result.Empty(),
)
