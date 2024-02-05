package data.remote.dto.saavn.newAlbums

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewAlbums(
    @SerialName("explicit_content")
    val explicitContent: String? = null,
    @SerialName("header_desc")
    val headerDesc: String? = null,
    val id: String? = null,
    val image: String? = null,
    val language: String? = null,
    val list: String? = null,
    @SerialName("list_count")
    val listCount: String? = null,
    @SerialName("list_type")
    val listType: String? = null,
    @SerialName("more_info")
    val moreInfo: MoreInfo? = null,
    @SerialName("perma_url")
    val permaUrl: String? = null,
    @SerialName("play_count")
    val playCount: String? = null,
    val subtitle: String? = null,
    val title: String? = null,
    val type: String? = null,
    val year: String? = null,
)
