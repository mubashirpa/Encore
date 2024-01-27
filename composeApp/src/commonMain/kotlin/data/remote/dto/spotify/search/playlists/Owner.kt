package data.remote.dto.spotify.search.playlists

import data.remote.dto.spotify.ExternalUrls
import data.remote.dto.spotify.Followers
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Owner(
    @SerialName("display_name")
    val displayName: String? = null,
    @SerialName("external_urls")
    val externalUrls: ExternalUrls? = null,
    val followers: Followers? = null,
    val href: String? = null,
    val id: String? = null,
    val type: String? = null,
    val uri: String? = null,
)
