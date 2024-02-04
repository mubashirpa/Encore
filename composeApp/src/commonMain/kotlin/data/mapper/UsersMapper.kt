package data.mapper

import data.remote.dto.spotify.users.currentUsersProfile.CurrentUsersProfileDto
import data.remote.dto.spotify.users.usersTopItems.ArtistItem
import data.remote.dto.spotify.users.usersTopItems.TrackItem
import domain.model.spotify.users.currentUsersProfile.CurrentUsersProfile
import domain.model.spotify.users.followedArtists.FollowedArtistsItem
import domain.model.spotify.users.usersTopItems.UsersTopArtistItem
import domain.model.spotify.users.usersTopItems.UsersTopTrackItem
import data.remote.dto.spotify.users.followedArtists.Item as FollowedArtistsItemDto

fun CurrentUsersProfileDto.toCurrentUsersProfile(): CurrentUsersProfile {
    return CurrentUsersProfile(
        displayName,
        email,
        id,
        images?.firstOrNull()?.url,
    )
}

fun FollowedArtistsItemDto.toFollowedArtistsItem(): FollowedArtistsItem {
    return FollowedArtistsItem(
        id,
        images?.firstOrNull()?.url,
        name,
    )
}

fun ArtistItem.toUsersTopArtistItem(): UsersTopArtistItem {
    return UsersTopArtistItem(
        id,
        images?.firstOrNull()?.url,
        name,
    )
}

fun TrackItem.toUsersTopTrackItem(): UsersTopTrackItem {
    return UsersTopTrackItem(
        artists?.joinToString(", ") { it.name.toString() },
        id,
        album?.images?.firstOrNull()?.url,
        name,
    )
}
