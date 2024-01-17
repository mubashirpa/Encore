package data.remote.dto.users

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Album(
    @SerialName("album_type")
    val albumType: String = "",
    val artists: List<Artists> = listOf(),
    @SerialName("available_markets")
    val availableMarkets: List<String> = listOf(),
    @SerialName("external_urls")
    val externalUrls: ExternalUrls = ExternalUrls(),
    val href: String = "",
    val id: String = "",
    val images: List<Image> = listOf(),
    val name: String = "",
    @SerialName("release_date")
    val releaseDate: String = "",
    @SerialName("release_date_precision")
    val releaseDatePrecision: String = "",
    val restrictions: Restrictions? = null,
    @SerialName("total_tracks")
    val totalTracks: Int = 0,
    val type: String = "",
    val uri: String = ""
)