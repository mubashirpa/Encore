package data.remote.dto.spotify.users.followedArtists

import kotlinx.serialization.Serializable

@Serializable
data class FollowedArtistsDto(
    val artists: Artists? = null,
)
