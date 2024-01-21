package data.mapper

import data.remote.dto.spotify.Image
import data.remote.dto.spotify.playlists.Item
import domain.model.spotify.playlists.PlaylistsItem
import domain.model.spotify.playlists.PlaylistsImage

fun Item.toPlaylistsItem(): PlaylistsItem {
    return PlaylistsItem(
        id,
        images?.map { it.toPlaylistsImage() },
        name
    )
}

private fun Image.toPlaylistsImage(): PlaylistsImage {
    return PlaylistsImage(url)
}