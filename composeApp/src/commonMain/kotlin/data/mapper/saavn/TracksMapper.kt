package data.mapper.saavn

import data.remote.dto.saavn.tracks.Song
import domain.model.saavn.tracks.SongItem

fun Song.toSongItem(): SongItem {
    return SongItem(
        moreInfo?.encryptedMediaUrl,
        id,
        image,
        subtitle,
        title,
    )
}
