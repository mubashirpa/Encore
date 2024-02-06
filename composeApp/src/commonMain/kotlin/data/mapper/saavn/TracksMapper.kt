package data.mapper.saavn

import data.remote.dto.saavn.tracks.Song
import domain.model.artists.Artist
import domain.model.tracks.Track
import data.remote.dto.saavn.tracks.Artist as SongArtist

fun Song.toTrack(): Track {
    return Track(
        artists = moreInfo?.artistMap?.artists?.map { it.toArtist() },
        id = id,
        image = image,
        name = title,
        mediaUrl = moreInfo?.encryptedMediaUrl,
    )
}

private fun SongArtist.toArtist(): Artist {
    return Artist(
        id = id,
        image = image,
        name = name,
    )
}
