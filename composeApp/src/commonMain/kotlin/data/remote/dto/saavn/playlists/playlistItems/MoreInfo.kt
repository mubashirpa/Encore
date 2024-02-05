package data.remote.dto.saavn.playlists.playlistItems

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoreInfo(
    val artists: List<Artist>? = null,
    @SerialName("fan_count")
    val fanCount: String? = null,
    val firstname: String? = null,
    @SerialName("follower_count")
    val followerCount: String? = null,
    // @SerialName("H2")
    // val h2: Any? = null,
    // val images: List<Any>? = null,
    @SerialName("is_dolby_content")
    val isDolbyContent: Boolean? = null,
    val isFY: Boolean? = null,
    @SerialName("is_followed")
    val isFollowed: String? = null,
    @SerialName("last_updated")
    val lastUpdated: String? = null,
    val lastname: String? = null,
    @SerialName("playlist_type")
    val playlistType: String? = null,
    val share: String? = null,
    // @SerialName("sub_types")
    // val subTypes: List<Any>? = null,
    // val subheading: Any? = null,
    @SerialName("subtitle_desc")
    val subtitleDesc: List<String?>? = null,
    // val subtype: List<Any>? = null,
    val uid: String? = null,
    val username: String? = null,
    @SerialName("video_count")
    val videoCount: String? = null,
)
