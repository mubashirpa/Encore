package domain.model.saavn.playlists

data class PlaylistItems(
    val image: String? = null,
    val list: List<PlaylistItem>? = null,
    val subtitle: String? = null,
    val title: String? = null,
)
