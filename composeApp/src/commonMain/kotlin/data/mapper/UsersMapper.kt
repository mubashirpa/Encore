package data.mapper

import data.remote.dto.spotify.Image
import data.remote.dto.spotify.users.Followers
import data.remote.dto.spotify.users.profile.UserDto
import data.remote.dto.spotify.users.top_items.Album
import data.remote.dto.spotify.users.top_items.ArtistItem
import data.remote.dto.spotify.users.top_items.TrackItem
import domain.model.spotify.users.UsersImage
import domain.model.spotify.users.profile.User
import domain.model.spotify.users.profile.UsersFollowers
import domain.model.spotify.users.top_items.UsersAlbum
import domain.model.spotify.users.top_items.UsersArtistItem
import domain.model.spotify.users.top_items.UsersTrackItem

fun UserDto.toUser(): User {
    return User(
        displayName,
        email,
        followers?.toUsersFollowers(),
        id,
        images?.map { it.toUsersImage() }
    )
}

fun ArtistItem.toUsersArtistItem(): UsersArtistItem {
    return UsersArtistItem(
        id,
        images?.map { it.toUsersImage() },
        name
    )
}

fun TrackItem.toUsersTrackItem(): UsersTrackItem {
    return UsersTrackItem(
        album?.toUsersAlbum(),
        id,
        isPlayable,
        name,
        previewUrl,
        uri
    )
}

private fun Followers.toUsersFollowers(): UsersFollowers {
    return UsersFollowers(
        total
    )
}

private fun Image.toUsersImage(): UsersImage {
    return UsersImage(url)
}

private fun Album.toUsersAlbum(): UsersAlbum {
    return UsersAlbum(
        images?.map { it.toUsersImage() }
    )
}