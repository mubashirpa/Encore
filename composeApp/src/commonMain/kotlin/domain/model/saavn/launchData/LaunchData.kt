package domain.model.saavn.launchData

data class LaunchData(
    val charts: List<LaunchDataItem>? = null,
    val newAlbums: List<LaunchDataItem>? = null,
    val newTrending: List<LaunchDataItem>? = null,
    val topPlaylists: List<LaunchDataItem>? = null,
)
