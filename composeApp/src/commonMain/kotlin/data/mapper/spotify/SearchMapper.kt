package data.mapper.spotify

import data.remote.dto.spotify.search.SearchDto
import domain.model.artists.Artist
import domain.model.playlists.Playlist
import domain.model.spotify.search.AlbumsItem
import domain.model.spotify.search.Search
import domain.model.spotify.search.ShowsItem
import domain.model.tracks.Track
import data.remote.dto.spotify.search.albums.Item as AlbumsItemDto
import data.remote.dto.spotify.search.artists.Item as ArtistsItemDto
import data.remote.dto.spotify.search.playlists.Item as PlaylistsItemDto
import data.remote.dto.spotify.search.shows.Item as ShowsItemDto
import data.remote.dto.spotify.search.tracks.Artist as TrackItemArtist
import data.remote.dto.spotify.search.tracks.Item as TracksItemDto

fun SearchDto.toSearch(): Search {
    return Search(
        albums?.items?.map { it.toAlbumsItem() },
        artists?.items?.map { it.toArtist() },
        playlists?.items?.map { it.toPlaylist() },
        shows?.items?.map { it.toShowsItem() },
        tracks?.items?.map { it.toTrack() },
    )
}

fun AlbumsItemDto.toAlbumsItem(): AlbumsItem {
    val typeAndYear = (if (totalTracks == 1) "Single • " else "").plus(releaseDate?.take(4))
    return AlbumsItem(
        artists?.joinToString(", ") { it.name.toString() },
        id,
        images?.firstOrNull()?.url,
        name,
        typeAndYear = typeAndYear,
    )
}

fun ArtistsItemDto.toArtist(): Artist {
    return Artist(
        id,
        images?.firstOrNull()?.url,
        name,
    )
}

fun PlaylistsItemDto.toPlaylist(): Playlist {
    return Playlist(
        description = description,
        id = id,
        image = images?.firstOrNull()?.url,
        name = name,
        owner = owner?.displayName,
        tracks = null,
    )
}

fun ShowsItemDto.toShowsItem(): ShowsItem {
    return ShowsItem(
        id,
        images?.firstOrNull()?.url,
        name,
        publisher,
    )
}

fun TracksItemDto.toTrack(): Track {
    return Track(
        artists = artists?.map { it.toArtist() },
        id = id,
        image = album?.images?.firstOrNull()?.url,
        name = name,
        mediaUrl = null,
    )
}

private fun TrackItemArtist.toArtist(): Artist {
    return Artist(
        id = id,
        image = images?.firstOrNull()?.url,
        name = name,
    )
}
