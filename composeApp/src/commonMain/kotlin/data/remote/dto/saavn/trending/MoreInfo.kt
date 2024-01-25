package data.remote.dto.saavn.trending

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoreInfo(
    val artistMap: ArtistMap? = null,
    @SerialName("fan_count")
    val fanCount: String? = null,
    val firstname: String? = null,
    @SerialName("follower_count")
    val followerCount: String? = null,
    val isWeekly: String? = null,
    @SerialName("release_date")
    val releaseDate: String? = null,
    @SerialName("song_count")
    val songCount: String? = null,
)
