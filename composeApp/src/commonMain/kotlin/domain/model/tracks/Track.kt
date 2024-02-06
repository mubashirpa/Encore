package domain.model.tracks

import core.utils.CryptoManager
import domain.model.artists.Artist

data class Track(
    val artists: List<Artist>? = null,
    val id: String? = null,
    val image: String? = null,
    val name: String? = null,
    val mediaUrl: String? = null,
) {
    val artistsNames: String? = artists?.joinToString(", ")
    private val cryptoManager = CryptoManager()
    val decryptedMediaUrl: String? =
        if (mediaUrl.isNullOrEmpty()) {
            null
        } else {
            cryptoManager.decrypt(mediaUrl)
        }
}
