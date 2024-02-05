package data.remote.dto.saavn.playlists.topPlaylists

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopPlaylists(
    @SerialName("explicit_content")
    val explicitContent: String? = null,
    val id: String? = null,
    val image: String? = null,
    @SerialName("mini_obj")
    val miniObj: Boolean? = null,
    @SerialName("more_info")
    val moreInfo: MoreInfo? = null,
    @SerialName("perma_url")
    val permaUrl: String? = null,
    val subtitle: String? = null,
    val title: String? = null,
    val type: String? = null,
)
