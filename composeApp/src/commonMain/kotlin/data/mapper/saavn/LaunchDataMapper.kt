package data.mapper.saavn

import data.remote.dto.saavn.charts.Charts
import data.remote.dto.saavn.launchData.LaunchDataDto
import data.remote.dto.saavn.newAlbums.NewAlbums
import data.remote.dto.saavn.newTrending.NewTrending
import data.remote.dto.saavn.playlists.topPlaylists.TopPlaylists
import domain.model.saavn.launchData.LaunchData
import domain.model.saavn.launchData.LaunchDataItem

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
