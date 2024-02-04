package presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import presentation.home.components.HomeListItem
import presentation.home.components.TopTracksListItem

@OptIn(ExperimentalResourceApi::class)
@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onEvent: (HomeUiEvent) -> Unit,
    accessToken: String,
) {
    LaunchedEffect(accessToken) {
        onEvent(HomeUiEvent.OnGetAccessToken(accessToken))
    }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
    ) {
        if (uiState.usersTrackItemResult is Result.Success) {
            val tracks = uiState.usersTrackItemResult.data.orEmpty()
            if (tracks.isNotEmpty()) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .heightIn(max = 250.dp),
                    contentPadding =
                        PaddingValues(
                            horizontal = 16.dp,
                            vertical = 12.dp,
                        ),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    userScrollEnabled = false,
                    content = {
                        items(
                            items = tracks,
                            key = { it.id!! },
                        ) { track ->
                            TopTracksListItem(
                                name = track.name.orEmpty(),
                                imageUrl = track.image.orEmpty(),
                                onClick = { /*TODO*/ },
                            )
                        }
                    },
                )
            }
        }

        if (uiState.featuredPlaylistsResult is Result.Success) {
            val featuredPlaylists = uiState.featuredPlaylistsResult.data.orEmpty()
            if (featuredPlaylists.isNotEmpty()) {
                Text(
                    text = stringResource(Res.string.popular),
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
                            items = featuredPlaylists,
                            key = { it.id!! },
                        ) { playlist ->
                            HomeListItem(
                                name = playlist.name.orEmpty(),
                                imageUrl = playlist.image.orEmpty(),
                                onClick = { /*TODO*/ },
                            )
                        }
                    },
                )
            }
        }

        if (uiState.launchDataResult is Result.Success) {
            val trendingList = uiState.launchDataResult.data?.newTrending.orEmpty()
            val topChartsList = uiState.launchDataResult.data?.charts.orEmpty()
            val newAlbumsList = uiState.launchDataResult.data?.newAlbums.orEmpty()
            val topPlaylistsList = uiState.launchDataResult.data?.topPlaylists.orEmpty()

            if (trendingList.isNotEmpty()) {
                Text(
                    text = stringResource(Res.string.trending_now),
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
                            items = trendingList,
                            key = { it.id!! },
                        ) { trending ->
                            HomeListItem(
                                name = trending.title.orEmpty(),
                                imageUrl = trending.image.orEmpty(),
                                onClick = { /*TODO*/ },
                            )
                        }
                    },
                )
            }

            if (topChartsList.isNotEmpty()) {
                Text(
                    text = stringResource(Res.string.top_charts),
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
                            items = topChartsList,
                            key = { it.id!! },
                        ) { topCharts ->
                            HomeListItem(
                                name = topCharts.title.orEmpty(),
                                imageUrl = topCharts.image.orEmpty(),
                                onClick = { /*TODO*/ },
                            )
                        }
                    },
                )
            }

            if (newAlbumsList.isNotEmpty()) {
                Text(
                    text = stringResource(Res.string.new_releases),
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
                            items = newAlbumsList,
                            key = { it.id!! },
                        ) { newAlbums ->
                            HomeListItem(
                                name = newAlbums.title.orEmpty(),
                                imageUrl = newAlbums.image.orEmpty(),
                                onClick = { /*TODO*/ },
                            )
                        }
                    },
                )
            }

            if (topPlaylistsList.isNotEmpty()) {
                Text(
                    text = stringResource(Res.string.editorial_picks),
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
                            items = topPlaylistsList,
                            key = { it.id!! },
                        ) { topPlaylists ->
                            HomeListItem(
                                name = topPlaylists.title.orEmpty(),
                                imageUrl = topPlaylists.image.orEmpty(),
                                onClick = { /*TODO*/ },
                            )
                        }
                    },
                )
            }
        }
    }
}
