package domain.model.users

data class UsersTopArtistItem(
    val id: String? = null,
    val images: List<UserTopItemImage>? = null,
    val name: String? = null
)

data class UsersTopTrackItem(
    val album: UsersTopItemAlbum? = null,
    val id: String? = null,
    val isPlayable: Boolean? = false,
    val name: String? = null,
    val previewUrl: String? = null,
    val uri: String? = null
)