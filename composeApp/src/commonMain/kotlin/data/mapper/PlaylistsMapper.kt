package data.mapper

import data.remote.dto.playlists.Image
import data.remote.dto.playlists.Item
import domain.model.playlists.PlaylistsItem
import domain.model.playlists.PlaylistsItemImage

fun Item.toPlaylistsItem(): PlaylistsItem {
    return PlaylistsItem(
        id,
        images?.map { it.toPlaylistsItemImage() },
        name
    )
}

private fun Image.toPlaylistsItemImage(): PlaylistsItemImage {
    return PlaylistsItemImage(url)
}