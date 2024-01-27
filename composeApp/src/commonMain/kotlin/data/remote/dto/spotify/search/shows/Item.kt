package data.remote.dto.spotify.search.shows

import data.remote.dto.spotify.Copyright
import data.remote.dto.spotify.ExternalUrls
import data.remote.dto.spotify.Image
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    @SerialName("available_markets")
    val availableMarkets: List<String>? = null,
    val copyrights: List<Copyright>? = null,
    val description: String? = null,
    val explicit: Boolean? = null,
    @SerialName("external_urls")
    val externalUrls: ExternalUrls? = null,
    val href: String? = null,
    @SerialName("html_description")
    val htmlDescription: String? = null,
    val id: String? = null,
    val images: List<Image>? = null,
    @SerialName("is_externally_hosted")
    val isExternallyHosted: Boolean? = null,
    val languages: List<String>? = null,
    @SerialName("media_type")
    val mediaType: String? = null,
    val name: String? = null,
    val publisher: String? = null,
    @SerialName("total_episodes")
    val totalEpisodes: Int? = null,
    val type: String? = null,
    val uri: String? = null,
)
