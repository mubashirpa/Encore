package data.remote.dto.spotify.search.episodes

import data.remote.dto.spotify.ExternalUrls
import data.remote.dto.spotify.Image
import data.remote.dto.spotify.Restrictions
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    @SerialName("audio_preview_url")
    val audioPreviewUrl: String? = null,
    val description: String? = null,
    @SerialName("duration_ms")
    val durationMs: Int? = null,
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
    @SerialName("is_playable")
    val isPlayable: Boolean? = null,
    val language: String? = null,
    val languages: List<String>? = null,
    val name: String? = null,
    @SerialName("release_date")
    val releaseDate: String? = null,
    @SerialName("release_date_precision")
    val releaseDatePrecision: String? = null,
    val restrictions: Restrictions? = null,
    @SerialName("resume_point")
    val resumePoint: ResumePoint? = null,
    val type: String? = null,
    val uri: String? = null,
)
