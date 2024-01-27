package data.remote.dto.spotify.search.audiobooks

import kotlinx.serialization.Serializable

@Serializable
data class Narrator(
    val name: String? = null,
)
