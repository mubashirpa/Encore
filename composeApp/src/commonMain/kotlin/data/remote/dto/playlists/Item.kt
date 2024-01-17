package data.remote.dto.playlists

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val collaborative: Boolean? = false,
    val description: String? = "",
    @SerialName("external_urls")
    val externalUrls: ExternalUrls? = ExternalUrls(),
    val href: String? = "",
    val id: String? = "",
    val images: List<Image>? = listOf(),
    val name: String? = "",
    val owner: Owner? = Owner(),
    @SerialName("primary_color")
    val primaryColor: String? = "",
    val `public`: Boolean? = false,
    @SerialName("snapshot_id")
    val snapshotId: String? = "",
    val tracks: Tracks? = Tracks(),
    val type: String? = "",
    val uri: String? = ""
)