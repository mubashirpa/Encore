package presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import presentation.home.components.HomeGridItem
import presentation.home.components.HomeListItem

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onEvent: (HomeUiEvent) -> Unit,
    accessToken: String
) {
    LaunchedEffect(accessToken) {
        onEvent(HomeUiEvent.OnGetAccessToken(accessToken))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        if (uiState.usersTrackItemResult is Result.Success) {
            val tracks = uiState.usersTrackItemResult.data.orEmpty()
            if (tracks.isNotEmpty()) {
                FlowRow(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .fillMaxWidth(1f),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    maxItemsInEachRow = 2
                ) {
                    tracks.forEach { track ->
                        HomeGridItem(
                            name = track.name.orEmpty(),
                            imageUrl = track.album?.images?.firstOrNull()?.url.orEmpty(),
                            modifier = Modifier.weight(1F, fill = true)
                        )
                    }
                }
            }
        }

        if (uiState.featuredPlaylistsResult is Result.Success) {
            val featuredPlaylists = uiState.featuredPlaylistsResult.data.orEmpty()
            if (featuredPlaylists.isNotEmpty()) {
                Text(
                    text = "Popular",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                    style = MaterialTheme.typography.titleMedium
                )
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 12.dp
                    ),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    content = {
                        items(
                            items = featuredPlaylists,
                            key = { it.id!! }
                        ) { playlist ->
                            HomeListItem(
                                name = playlist.name.orEmpty(),
                                imageUrl = playlist.images?.firstOrNull()?.url.orEmpty()
                            )
                        }
                    }
                )
            }
        }
    }
}