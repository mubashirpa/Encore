package data.mapper.saavn

import data.remote.dto.saavn.playlists.playlistItems.PlaylistItemsDto
import domain.model.artists.Artist
import domain.model.playlists.Playlist
import domain.model.tracks.Track
import data.remote.dto.saavn.playlists.playlistItems.Artist as PlaylistArtist
import data.remote.dto.saavn.playlists.playlistItems.Playlist as PlaylistItem

fun PlaylistItemsDto.toPlaylist(): Playlist {
    return Playlist(
        description = headerDesc,
        id = id,
        image = image,
        name = title,
        owner = moreInfo?.firstname.plus(moreInfo?.lastname),
        tracks = list?.map { it.toTrack() },
    )
}

private fun PlaylistItem.toTrack(): Track {
    return Track(
        artists = moreInfo?.artistMap?.artists?.map { it.toArtist() },
        id = id,
        image = image,
        name = title,
        mediaUrl = null,
    )
}

private fun PlaylistArtist.toArtist(): Artist {
    return Artist(
        id = id,
        image = image,
        name = name,
    )
}
