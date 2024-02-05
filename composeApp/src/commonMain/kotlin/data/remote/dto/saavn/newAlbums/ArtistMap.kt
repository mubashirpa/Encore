package data.remote.dto.saavn.newAlbums

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtistMap(
    val artists: List<Artist>? = null,
    @SerialName("featured_artists")
    val featuredArtists: List<Artist>? = null,
    @SerialName("primary_artists")
    val primaryArtists: List<Artist>? = null,
)
