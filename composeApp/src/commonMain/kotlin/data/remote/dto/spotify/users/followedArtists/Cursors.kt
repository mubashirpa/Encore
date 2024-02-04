package data.remote.dto.spotify.users.followedArtists

import kotlinx.serialization.Serializable

@Serializable
data class Cursors(
    val after: String? = null,
    val before: String? = null,
)
