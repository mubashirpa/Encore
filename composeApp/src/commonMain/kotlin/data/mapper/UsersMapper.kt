package data.mapper

import data.remote.dto.users.Album
import data.remote.dto.users.ArtistItem
import data.remote.dto.users.Image
import data.remote.dto.users.TrackItem
import domain.model.users.UserTopItemImage
import domain.model.users.UsersTopArtistItem
import domain.model.users.UsersTopItemAlbum
import domain.model.users.UsersTopTrackItem

fun ArtistItem.toUsersTopArtistItem(): UsersTopArtistItem {
    return UsersTopArtistItem(
        id,
        images?.map { it.toUserTopItemImage() },
        name
    )
}

fun TrackItem.toUsersTopTrackItem(): UsersTopTrackItem {
    return UsersTopTrackItem(
        album?.toUsersTopAlbum(),
        id,
        isPlayable,
        name,
        previewUrl,
        uri
    )
}

private fun Album.toUsersTopAlbum(): UsersTopItemAlbum {
    return UsersTopItemAlbum(
        images.map { it.toUserTopItemImage() }
    )
}

private fun Image.toUserTopItemImage(): UserTopItemImage {
    return UserTopItemImage(url)
}