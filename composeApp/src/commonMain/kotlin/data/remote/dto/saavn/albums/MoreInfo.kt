package data.remote.dto.saavn.albums

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoreInfo(
    val album: String? = null,
    @SerialName("album_id")
    val albumId: String? = null,
    @SerialName("album_url")
    val albumUrl: String? = null,
    val artistMap: ArtistMap? = null,
    @SerialName("cache_state")
    val cacheState: String? = null,
    @SerialName("copyright_text")
    val copyrightText: String? = null,
    val duration: String? = null,
    @SerialName("encrypted_cache_url")
    val encryptedCacheUrl: String? = null,
    @SerialName("encrypted_media_url")
    val encryptedMediaUrl: String? = null,
    @SerialName("has_lyrics")
    val hasLyrics: String? = null,
    @SerialName("is_dolby_content")
    val isDolbyContent: Boolean? = null,
    @SerialName("320kbps")
    val kbps: String? = null,
    val label: String? = null,
    @SerialName("label_url")
    val labelUrl: String? = null,
    @SerialName("lyrics_snippet")
    val lyricsSnippet: String? = null,
    val music: String? = null,
    val origin: String? = null,
    @SerialName("release_date")
    val releaseDate: String? = null,
    @SerialName("request_jiotune_flag")
    val requestJiotuneFlag: Boolean? = null,
    val rights: Rights? = null,
    @SerialName("song_count")
    val songCount: String? = null,
    val starred: String? = null,
    @SerialName("triller_available")
    val trillerAvailable: Boolean? = null,
    val vcode: String? = null,
    val vlink: String? = null,
    val webp: String? = null,
)
