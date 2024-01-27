package data.remote.dto.spotify.search.audiobooks

import data.remote.dto.spotify.Copyright
import data.remote.dto.spotify.ExternalUrls
import data.remote.dto.spotify.Image
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val authors: List<Author>? = null,
    @SerialName("available_markets")
    val availableMarkets: List<String>? = null,
    val copyrights: List<Copyright>? = null,
    val description: String? = null,
    val edition: String? = null,
    val explicit: Boolean? = false,
    @SerialName("external_urls")
    val externalUrls: ExternalUrls? = null,
    val href: String? = null,
    @SerialName("html_description")
    val htmlDescription: String? = null,
    val id: String? = null,
    val images: List<Image>? = null,
    val languages: List<String>? = null,
    @SerialName("media_type")
    val mediaType: String? = null,
    val name: String? = null,
    val narrators: List<Narrator>? = null,
    val publisher: String? = null,
    @SerialName("total_chapters")
    val totalChapters: Int? = null,
    val type: String? = null,
    val uri: String? = null,
)
