package domain.model.playlists

data class PlaylistsItem(
    val id: String? = "",
    val images: List<PlaylistsItemImage>? = listOf(),
    val name: String? = ""
)