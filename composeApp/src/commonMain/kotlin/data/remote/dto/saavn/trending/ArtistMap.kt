package data.remote.dto.saavn.trending

import kotlinx.serialization.Serializable

@Serializable
data class ArtistMap(
    val artists: List<Artist>? = null,
)
