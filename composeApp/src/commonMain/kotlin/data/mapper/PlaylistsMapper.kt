package data.mapper

import data.remote.dto.spotify.Image
import data.remote.dto.spotify.playlists.Item
import domain.model.spotify.playlists.PlaylistsImage
import domain.model.spotify.playlists.PlaylistsItem

fun Item.toPlaylistsItem(): PlaylistsItem {
    return PlaylistsItem(
        id,
        images?.map { it.toPlaylistsImage() },
        name,
    )
}

private fun Image.toPlaylistsImage(): PlaylistsImage {
    return PlaylistsImage(url)
}
