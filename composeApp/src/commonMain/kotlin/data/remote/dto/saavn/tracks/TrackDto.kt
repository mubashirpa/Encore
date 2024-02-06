package data.remote.dto.saavn.tracks

import kotlinx.serialization.Serializable

@Serializable
data class TrackDto(
    // val modules: Modules? = null,
    val songs: List<Song>? = null,
)
