package domain.repository

import data.remote.dto.saavn.launchData.LaunchDataDto
import data.remote.dto.saavn.playlists.playlistItems.PlaylistItemsDto
import data.remote.dto.saavn.tracks.TrackDto

interface SaavnRepository {
    suspend fun getLaunchData(languages: List<String> = listOf("english")): LaunchDataDto

    suspend fun getPlaylistItems(playlistId: String): PlaylistItemsDto

    suspend fun getTrack(id: String): TrackDto
}
