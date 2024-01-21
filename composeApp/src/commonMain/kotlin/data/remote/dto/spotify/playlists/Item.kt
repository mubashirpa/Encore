package data.remote.dto.spotify.playlists

import data.remote.dto.spotify.ExternalUrls
import data.remote.dto.spotify.Image
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val collaborative: Boolean? = null,
    val description: String? = null,
    @SerialName("external_urls")
    val externalUrls: ExternalUrls? = null,
    val href: String? = null,
    val id: String? = null,
    val images: List<Image>? = null,
    val name: String? = null,
    val owner: Owner? = null,
    @SerialName("primary_color")
    val primaryColor: String? = null,
    val `public`: Boolean? = null,
    @SerialName("snapshot_id")
    val snapshotId: String? = null,
    val tracks: Tracks? = null,
    val type: String? = null,
    val uri: String? = null
)