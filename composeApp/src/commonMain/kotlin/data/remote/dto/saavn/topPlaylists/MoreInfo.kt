package data.remote.dto.saavn.topPlaylists

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoreInfo(
    val firstname: String? = null,
    @SerialName("follower_count")
    val followerCount: String? = null,
    @SerialName("last_updated")
    val lastUpdated: String? = null,
    @SerialName("song_count")
    val songCount: String? = null,
    val uid: String? = null,
)
