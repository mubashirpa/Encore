package data.remote.dto.saavn

import data.remote.dto.saavn.albums.NewAlbums
import data.remote.dto.saavn.charts.Charts
import data.remote.dto.saavn.playlists.TopPlaylists
import data.remote.dto.saavn.trending.NewTrending
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LaunchDataDto(
    val charts: List<Charts>? = null,
    @SerialName("new_albums")
    val newAlbums: List<NewAlbums>? = null,
    @SerialName("new_trending")
    val newTrending: List<NewTrending>? = null,
    @SerialName("top_playlists")
    val topPlaylists: List<TopPlaylists>? = null,
)
