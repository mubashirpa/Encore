package data.remote.dto.spotify.search.audiobooks

import kotlinx.serialization.Serializable

@Serializable
data class Author(
    val name: String? = null,
)
