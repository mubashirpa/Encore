package data.remote.dto.saavn.albums

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtistMap(
    val artists: List<Artist?>? = null,
    @SerialName("primary_artists")
    val primaryArtists: List<PrimaryArtist>? = null,
)
