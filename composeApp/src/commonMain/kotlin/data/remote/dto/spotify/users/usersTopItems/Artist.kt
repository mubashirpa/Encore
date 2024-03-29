package data.remote.dto.spotify.users.usersTopItems

import data.remote.dto.spotify.ExternalUrls
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Artist(
    @SerialName("external_urls")
    val externalUrls: ExternalUrls? = null,
    val href: String? = null,
    val id: String? = null,
    val name: String? = null,
    val type: String? = null,
    val uri: String? = null,
)
