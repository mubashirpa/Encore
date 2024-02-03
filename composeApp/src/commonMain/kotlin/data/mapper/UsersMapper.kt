package data.mapper

import data.remote.dto.spotify.Followers
import data.remote.dto.spotify.Image
import data.remote.dto.spotify.users.profile.UserDto
import data.remote.dto.spotify.users.topItems.ArtistItem
import data.remote.dto.spotify.users.topItems.TrackItem
import domain.model.spotify.users.profile.User
import domain.model.spotify.users.profile.UsersFollowers
import domain.model.spotify.users.profile.UsersImage
import domain.model.spotify.users.topItems.UsersTopArtistItem
import domain.model.spotify.users.topItems.UsersTopTrackItem

fun UserDto.toUser(): User {
    return User(
        displayName,
        email,
        followers?.toUsersFollowers(),
        id,
        images?.map { it.toUsersImage() },
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
        id,
        album?.images?.firstOrNull()?.url,
        name,
    )
}

private fun Followers.toUsersFollowers(): UsersFollowers {
    return UsersFollowers(
        total,
    )
}

private fun Image.toUsersImage(): UsersImage {
    return UsersImage(url)
}
