package data.remote.dto.spotify.search.tracks

import data.remote.dto.spotify.ExternalUrls
import data.remote.dto.spotify.Followers
import data.remote.dto.spotify.Image
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Artist(
    @SerialName("external_urls")
    val externalUrls: ExternalUrls? = null,
    val followers: Followers? = null,
    val genres: List<String>? = null,
    val href: String? = null,
    val id: String? = null,
    val images: List<Image>? = null,
    val name: String? = null,
    val popularity: Int? = null,
    val type: String? = null,
    val uri: String? = null,
)
