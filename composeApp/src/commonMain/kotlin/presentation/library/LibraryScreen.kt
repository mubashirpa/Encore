package presentation.library

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.Result
import encore.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import presentation.library.components.ArtistsListItem
import presentation.library.components.PlaylistsListItem
import presentation.library.components.TracksListItem

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LibraryScreen(
    uiState: LibraryUiState,
    onEvent: (LibraryUiEvent) -> Unit,
    accessToken: String,
) {
    LaunchedEffect(accessToken) {
        onEvent(LibraryUiEvent.OnGetAccessToken(accessToken))
    }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
    ) {
        if (uiState.followedArtists is Result.Success) {
            val followedArtists = uiState.followedArtists.data.orEmpty()
            if (followedArtists.isNotEmpty()) {
                Text(
                    text = stringResource(Res.string.artists),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                    style = MaterialTheme.typography.titleMedium,
                )
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding =
                        PaddingValues(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 12.dp,
                        ),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    content = {
                        items(
                            items = followedArtists,
                            key = { it.id!! },
                        ) { artist ->
                            ArtistsListItem(
                                imageUrl = artist.image.orEmpty(),
                                onClick = { /*TODO*/ },
                            )
                        }
                    },
                )
            }
        }

        if (uiState.usersPlaylists is Result.Success) {
            val usersPlaylists = uiState.usersPlaylists.data.orEmpty()
            if (usersPlaylists.isNotEmpty()) {
                Text(
                    text = stringResource(Res.string.playlists),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                    style = MaterialTheme.typography.titleMedium,
                )
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding =
                        PaddingValues(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 12.dp,
                        ),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    content = {
                        items(
                            items = usersPlaylists,
                            key = { it.id!! },
                        ) { playlist ->
                            PlaylistsListItem(
                                name = playlist.name.orEmpty(),
                                imageUrl = playlist.image.orEmpty(),
                                artists = playlist.owner,
                                onClick = { /*TODO*/ },
                            )
                        }
                    },
                )
            }
        }

        if (uiState.usersTopTracks is Result.Success) {
            val usersTopTracks = uiState.usersTopTracks.data.orEmpty()
            if (usersTopTracks.isNotEmpty()) {
                Text(
                    text = stringResource(Res.string.songs),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                    style = MaterialTheme.typography.titleMedium,
                )
                usersTopTracks.forEach { track ->
                    TracksListItem(
                        name = track.name.orEmpty(),
                        imageUrl = track.image.orEmpty(),
                        artists = track.artists.orEmpty(),
                        onClick = { /*TODO*/ },
                    )
                }
            }
        }
    }
}
