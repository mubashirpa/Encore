package data.remote.dto.playlists

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Owner(
    @SerialName("display_name")
    val displayName: String? = "",
    @SerialName("external_urls")
    val externalUrls: ExternalUrls? = ExternalUrls(),
    val href: String? = "",
    val id: String? = "",
    val type: String? = "",
    val uri: String? = ""
)