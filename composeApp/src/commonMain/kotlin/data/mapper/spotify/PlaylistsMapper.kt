package data.mapper.spotify

import domain.model.playlists.Playlist
import data.remote.dto.spotify.playlists.currentUsersPlaylists.Item as CurrentUsersPlaylistsItem
import data.remote.dto.spotify.playlists.featuredPlaylists.Item as FeaturedPlaylistsItem

fun CurrentUsersPlaylistsItem.toPlaylist(): Playlist {
    return Playlist(
        description = description,
        id = id,
        image = images?.firstOrNull()?.url,
        name = name,
        owner = owner?.displayName,
        tracks = null,
    )
}

fun FeaturedPlaylistsItem.toPlaylist(): Playlist {
    return Playlist(
        description = description,
        id = id,
        image = images?.firstOrNull()?.url,
        name = name,
        owner = owner?.displayName,
        tracks = null,
    )
}
