package data.mapper

import data.remote.dto.saavn.LaunchDataDto
import data.remote.dto.saavn.albums.NewAlbums
import data.remote.dto.saavn.charts.Charts
import data.remote.dto.saavn.playlists.TopPlaylists
import data.remote.dto.saavn.trending.NewTrending
import domain.model.saavn.LaunchData
import domain.model.saavn.LaunchDataItem

fun LaunchDataDto.toLaunchData(): LaunchData {
    return LaunchData(
        charts?.map { it.toLaunchDataItem() },
        newAlbums?.map { it.toLaunchDataItem() },
        newTrending?.map { it.toLaunchDataItem() },
        topPlaylists?.map { it.toLaunchDataItem() },
    )
}

private fun Charts.toLaunchDataItem(): LaunchDataItem {
    return LaunchDataItem(id, image, subtitle, title)
}

private fun NewAlbums.toLaunchDataItem(): LaunchDataItem {
    return LaunchDataItem(id, image, subtitle, title)
}

private fun NewTrending.toLaunchDataItem(): LaunchDataItem {
    return LaunchDataItem(id, image, subtitle, title)
}

private fun TopPlaylists.toLaunchDataItem(): LaunchDataItem {
    return LaunchDataItem(id, image, subtitle, title)
}
