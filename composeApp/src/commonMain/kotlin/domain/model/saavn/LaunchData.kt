package domain.model.saavn

data class LaunchData(
    val charts: List<LaunchDataItem>? = null,
    val newAlbums: List<LaunchDataItem>? = null,
    val newTrending: List<LaunchDataItem>? = null,
    val topPlaylists: List<LaunchDataItem>? = null,
)
