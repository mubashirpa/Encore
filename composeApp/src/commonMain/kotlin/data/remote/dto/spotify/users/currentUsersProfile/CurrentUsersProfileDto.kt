package data.remote.dto.spotify.users.currentUsersProfile

import data.remote.dto.spotify.ExternalUrls
import data.remote.dto.spotify.Followers
import data.remote.dto.spotify.Image
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentUsersProfileDto(
    val country: String? = null,
    @SerialName("display_name")
    val displayName: String? = null,
    val email: String? = null,
    @SerialName("explicit_content")
    val explicitContent: ExplicitContent? = null,
    @SerialName("external_urls")
    val externalUrls: ExternalUrls? = null,
    val followers: Followers? = null,
    val href: String? = null,
    val id: String? = null,
    val images: List<Image>? = null,
    val product: String? = null,
    val type: String? = null,
    val uri: String? = null,
)
