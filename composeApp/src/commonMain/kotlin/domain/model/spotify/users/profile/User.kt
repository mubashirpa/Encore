package domain.model.spotify.users.profile

import domain.model.spotify.users.UsersImage

data class User(
    val displayName: String? = null,
    val email: String? = null,
    val followers: UsersFollowers? = null,
    val id: String? = null,
    val images: List<UsersImage>? = null,
)
