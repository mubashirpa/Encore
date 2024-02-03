package domain.model.spotify.users.topItems

import domain.model.spotify.users.UsersImage

data class UsersArtistItem(
    val id: String? = null,
    val images: List<UsersImage>? = null,
    val name: String? = null,
)

data class UsersTrackItem(
    val album: UsersAlbum? = null,
    val id: String? = null,
    val isPlayable: Boolean? = false,
    val name: String? = null,
    val previewUrl: String? = null,
    val uri: String? = null,
)
