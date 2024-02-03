package data.mapper

import data.remote.dto.spotify.Followers
import data.remote.dto.spotify.Image
import data.remote.dto.spotify.users.profile.UserDto
import data.remote.dto.spotify.users.topItems.Album
import data.remote.dto.spotify.users.topItems.ArtistItem
import data.remote.dto.spotify.users.topItems.TrackItem
import domain.model.spotify.users.UsersImage
import domain.model.spotify.users.profile.User
import domain.model.spotify.users.profile.UsersFollowers
import domain.model.spotify.users.topItems.UsersAlbum
import domain.model.spotify.users.topItems.UsersArtistItem
import domain.model.spotify.users.topItems.UsersTrackItem

fun UserDto.toUser(): User {
    return User(
        displayName,
        email,
        followers?.toUsersFollowers(),
        id,
        images?.map { it.toUsersImage() },
    )
}

fun ArtistItem.toUsersArtistItem(): UsersArtistItem {
    return UsersArtistItem(
        id,
        images?.map { it.toUsersImage() },
        name,
    )
}

fun TrackItem.toUsersTrackItem(): UsersTrackItem {
    return UsersTrackItem(
        album?.toUsersAlbum(),
        id,
        isPlayable,
        name,
        previewUrl,
        uri,
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

private fun Album.toUsersAlbum(): UsersAlbum {
    return UsersAlbum(
        images?.map { it.toUsersImage() },
    )
}
