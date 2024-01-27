package data.remote.dto.spotify.users.top_items

import data.remote.dto.spotify.ExternalUrls
import data.remote.dto.spotify.Image
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Album(
    @SerialName("album_type")
    val albumType: String? = null,
    val artists: List<Artists>? = null,
    @SerialName("available_markets")
    val availableMarkets: List<String>? = null,
    @SerialName("external_urls")
    val externalUrls: ExternalUrls? = null,
    val href: String? = null,
    val id: String? = null,
    val images: List<Image>? = null,
    val name: String? = null,
    @SerialName("release_date")
    val releaseDate: String? = null,
    @SerialName("release_date_precision")
    val releaseDatePrecision: String? = null,
    val restrictions: Restrictions? = null,
    @SerialName("total_tracks")
    val totalTracks: Int? = null,
    val type: String? = null,
    val uri: String? = null,
)
