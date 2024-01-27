package data.remote.dto.spotify.search.tracks

import data.remote.dto.spotify.ExternalIds
import data.remote.dto.spotify.ExternalUrls
import data.remote.dto.spotify.Restrictions
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val album: Album? = null,
    val artists: List<Artist>? = null,
    @SerialName("available_markets")
    val availableMarkets: List<String>? = null,
    @SerialName("disc_number")
    val discNumber: Int? = null,
    @SerialName("duration_ms")
    val durationMs: Int? = null,
    val explicit: Boolean? = null,
    @SerialName("external_ids")
    val externalIds: ExternalIds? = null,
    @SerialName("external_urls")
    val externalUrls: ExternalUrls? = null,
    val href: String? = null,
    val id: String? = null,
    @SerialName("is_local")
    val isLocal: Boolean? = null,
    @SerialName("is_playable")
    val isPlayable: Boolean? = null,
    @SerialName("linked_from")
    val linkedFrom: LinkedFrom? = null,
    val name: String? = null,
    val popularity: Int? = null,
    @SerialName("preview_url")
    val previewUrl: String? = null,
    val restrictions: Restrictions? = null,
    @SerialName("track_number")
    val trackNumber: Int? = null,
    val type: String? = null,
    val uri: String? = null,
)
