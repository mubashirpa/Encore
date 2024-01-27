package data.remote.dto.spotify.search.episodes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResumePoint(
    @SerialName("fully_played")
    val fullyPlayed: Boolean? = null,
    @SerialName("resume_position_ms")
    val resumePositionMs: Int? = null,
)
