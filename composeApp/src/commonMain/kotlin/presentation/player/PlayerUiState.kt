package presentation.player

import core.Result
import domain.model.tracks.Track

data class PlayerUiState(
    val trackImageUrl: String = "",
    val trackId: String = "",
    val trackResult: Result<Track> = Result.Empty(),
)
