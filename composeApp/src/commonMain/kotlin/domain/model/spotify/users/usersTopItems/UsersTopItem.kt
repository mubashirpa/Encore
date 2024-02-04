package domain.model.spotify.users.usersTopItems

data class UsersTopArtistItem(
    val id: String? = null,
    val image: String? = null,
    val name: String? = null,
)

data class UsersTopTrackItem(
    val artists: String? = null,
    val id: String? = null,
    val image: String? = null,
    val name: String? = null,
)
