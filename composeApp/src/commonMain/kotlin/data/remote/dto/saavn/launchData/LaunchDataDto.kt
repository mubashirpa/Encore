package data.remote.dto.saavn.launchData

import data.remote.dto.saavn.charts.Charts
import data.remote.dto.saavn.newAlbums.NewAlbums
import data.remote.dto.saavn.newTrending.NewTrending
import data.remote.dto.saavn.topPlaylists.TopPlaylists
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
