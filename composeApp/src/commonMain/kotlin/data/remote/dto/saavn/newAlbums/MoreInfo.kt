package data.remote.dto.saavn.newAlbums

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoreInfo(
    val artistMap: ArtistMap? = null,
    @SerialName("release_date")
    val releaseDate: String? = null,
    @SerialName("song_count")
    val songCount: String? = null,
)
