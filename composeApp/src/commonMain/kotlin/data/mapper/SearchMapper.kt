package data.mapper

import data.remote.dto.spotify.search.SearchDto
import domain.model.spotify.search.AlbumsItem
import domain.model.spotify.search.ArtistsItem
import domain.model.spotify.search.PlaylistsItem
import domain.model.spotify.search.Search
import domain.model.spotify.search.ShowsItem
import domain.model.spotify.search.TracksItem
import data.remote.dto.spotify.search.albums.Item as AlbumsItemDto
import data.remote.dto.spotify.search.artists.Item as ArtistsItemDto
import data.remote.dto.spotify.search.playlists.Item as PlaylistsItemDto
import data.remote.dto.spotify.search.shows.Item as ShowsItemDto
import data.remote.dto.spotify.search.tracks.Item as TracksItemDto

fun SearchDto.toSearch(): Search {
    return Search(
        albums?.items?.map { it.toSearchItem() },
        artists?.items?.map { it.toArtistsItem() },
        playlists?.items?.map { it.toPlaylistsItem() },
        shows?.items?.map { it.toShowsItem() },
        tracks?.items?.map { it.toTracksItem() },
    )
}

fun AlbumsItemDto.toSearchItem(): AlbumsItem {
    val typeAndYear = (if (totalTracks == 1) "Single • " else "").plus(releaseDate?.take(4))
    return AlbumsItem(
        artists?.joinToString(", ") { it.name.toString() },
        id,
        images?.firstOrNull()?.url,
        name,
        typeAndYear = typeAndYear,
    )
}

fun ArtistsItemDto.toArtistsItem(): ArtistsItem {
    return ArtistsItem(
        id,
        images?.firstOrNull()?.url,
        name,
    )
}

fun PlaylistsItemDto.toPlaylistsItem(): PlaylistsItem {
    return PlaylistsItem(
        id,
        images?.firstOrNull()?.url,
        name,
        owner?.displayName,
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

fun TracksItemDto.toTracksItem(): TracksItem {
    return TracksItem(
        artists?.joinToString(", ") { it.name.toString() },
        id,
        album?.images?.firstOrNull()?.url,
        name,
    )
}
