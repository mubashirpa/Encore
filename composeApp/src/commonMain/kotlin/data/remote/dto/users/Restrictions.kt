package data.remote.dto.users

import kotlinx.serialization.Serializable

@Serializable
data class Restrictions(
    val reason: String? = ""
)