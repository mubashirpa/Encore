package data.mapper.saavn

import data.remote.dto.saavn.playlists.playlistItems.Playlist
import data.remote.dto.saavn.playlists.playlistItems.PlaylistItemsDto
import domain.model.saavn.playlists.PlaylistItem
import domain.model.saavn.playlists.PlaylistItems

fun PlaylistItemsDto.toPlaylistItems(): PlaylistItems {
    return PlaylistItems(
        image,
        list?.map { it.toPlaylistItem() },
        subtitle,
        title,
    )
}

private fun Playlist.toPlaylistItem(): PlaylistItem {
    return PlaylistItem(
        id,
        image,
        subtitle,
        title,
    )
}
