package data.remote.dto.saavn.playlist

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rights(
    val cacheable: String? = null,
    val code: String? = null,
    @SerialName("delete_cached_object")
    val deleteCachedObject: String? = null,
    val reason: String? = null,
)
