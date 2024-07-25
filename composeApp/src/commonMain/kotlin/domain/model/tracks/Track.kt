package domain.model.tracks

import core.utils.CryptoManager
import domain.model.artists.Artist
import kotlinx.serialization.Serializable

@Serializable
data class Track(
    val artists: List<Artist>? = null,
    val id: String? = null,
    val image: String? = null,
    val name: String? = null,
    val mediaUrl: String? = null,
) {
    private val cryptoManager = CryptoManager()
    val artistsNames: String? by lazy {
        artists?.joinToString(", ") {
            it.name.toString()
        }?.replace("&quot;", "\"")?.replace("&#039;", "'")?.replace("&amp;", "&")
    }
    val decryptedMediaUrl: String? by lazy {
        if (mediaUrl.isNullOrEmpty()) {
            null
        } else {
            cryptoManager.decrypt(mediaUrl)
        }
    }
    val formattedName by lazy {
        name?.replace("&quot;", "\"")?.replace("&#039;", "'")?.replace("&amp;", "&")
    }
}
