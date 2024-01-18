package data.remote.dto.users

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtistItem(
    @SerialName("external_urls")
    val externalUrls: ExternalUrls? = null,
    val followers: Followers? = null,
    val genres: List<String>? = null,
    val href: String? = null,
    val id: String? = null,
    val images: List<Image>? = null,
    val name: String? = null,
    val popularity: Int? = 0,
    val type: String? = null,
    val uri: String? = null
)

@Serializable
data class TrackItem(
    val album: Album? = null,
    val artists: List<Artists>? = null,
    @SerialName("available_markets")
    val availableMarkets: List<String>? = null,
    @SerialName("disc_number")
    val discNumber: Int? = 0,
    @SerialName("duration_ms")
    val durationMs: Int? = 0,
    val explicit: Boolean? = false,
    @SerialName("external_ids")
    val externalIds: ExternalIds? = null,
    @SerialName("external_urls")
    val externalUrls: ExternalUrls? = null,
    val href: String? = null,
    val id: String? = null,
    @SerialName("is_local")
    val isLocal: Boolean? = false,
    @SerialName("is_playable")
    val isPlayable: Boolean? = false,
    val name: String? = null,
    val popularity: Int? = 0,
    @SerialName("preview_url")
    val previewUrl: String? = null,
    val restrictions: Restrictions? = null,
    @SerialName("track_number")
    val trackNumber: Int? = 0,
    val type: String? = null,
    val uri: String? = null
)
