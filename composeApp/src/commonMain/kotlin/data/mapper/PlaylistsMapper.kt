package data.mapper

import domain.model.spotify.playlists.PlaylistsItem
import data.remote.dto.spotify.playlists.currentUsersPlaylists.Item as CurrentUsersPlaylistsItem
import data.remote.dto.spotify.playlists.featuredPlaylists.Item as FeaturedPlaylistsItem

fun CurrentUsersPlaylistsItem.toCurrentUsersPlaylistsItem(): PlaylistsItem {
    return PlaylistsItem(
        id,
        images?.firstOrNull()?.url,
        name,
        owner?.displayName,
    )
}

fun FeaturedPlaylistsItem.toFeaturedPlaylistsItem(): PlaylistsItem {
    return PlaylistsItem(
        id,
        images?.firstOrNull()?.url,
        name,
        owner?.displayName,
    )
}
