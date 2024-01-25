package data.remote.dto.saavn.albums

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PrimaryArtist(
    val id: String? = null,
    val image: String? = null,
    val name: String? = null,
    @SerialName("perma_url")
    val permaUrl: String? = null,
    val role: String? = null,
    val type: String? = null,
)