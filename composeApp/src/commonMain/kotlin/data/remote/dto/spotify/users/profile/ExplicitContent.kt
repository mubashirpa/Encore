package data.remote.dto.spotify.users.profile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExplicitContent(
    @SerialName("filter_enabled")
    val filterEnabled: Boolean? = null,
    @SerialName("filter_locked")
    val filterLocked: Boolean? = null
)