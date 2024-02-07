package data.mapper.spotify

import data.remote.dto.spotify.users.currentUsersProfile.CurrentUsersProfileDto
import data.remote.dto.spotify.users.usersTopItems.ArtistItem
import data.remote.dto.spotify.users.usersTopItems.TrackItem
import domain.model.artists.Artist
import domain.model.spotify.users.CurrentUsersProfile
import domain.model.tracks.Track
import data.remote.dto.spotify.users.followedArtists.Item as FollowedArtistsItemDto
import data.remote.dto.spotify.users.usersTopItems.Artist as UsersTopTracksArtist

fun CurrentUsersProfileDto.toCurrentUsersProfile(): CurrentUsersProfile {
    return CurrentUsersProfile(
        displayName = displayName,
        email = email,
        id = id,
        image = images?.firstOrNull()?.url,
    )
}

fun FollowedArtistsItemDto.toArtist(): Artist {
    return Artist(
        id = id,
        image = images?.firstOrNull()?.url,
        name = name,
    )
}

fun ArtistItem.toArtist(): Artist {
    return Artist(
        id = id,
        image = images?.firstOrNull()?.url,
        name = name,
    )
}

fun TrackItem.toTrack(): Track {
    return Track(
        artists = artists?.map { it.toArtist() },
        id = id,
        image = album?.images?.firstOrNull()?.url,
        name = name,
    )
}

private fun UsersTopTracksArtist.toArtist(): Artist {
    return Artist(
        id = id,
        image = null,
        name = name,
    )
}
