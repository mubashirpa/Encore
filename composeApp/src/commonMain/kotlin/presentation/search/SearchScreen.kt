package presentation.search

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import core.Result
import domain.model.artists.Artist
import domain.model.playlists.Playlist
import domain.model.spotify.search.AlbumsItem
import domain.model.spotify.search.ShowsItem
import domain.model.tracks.Track
import domain.repository.SearchItemType
import encore.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import presentation.search.components.AlbumsListItem
import presentation.search.components.ArtistsListItem
import presentation.search.components.CategoriesListItem
import presentation.search.components.PlalistsListItem
import presentation.search.components.SearchBar
import presentation.search.components.ShowsListItem
import presentation.search.components.TracksListItem
import presentation.utils.header

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun SearchScreen(
    uiState: SearchUiState,
    onEvent: (SearchUiEvent) -> Unit,
    accessToken: String,
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val filterItems: List<Pair<String, SearchItemType>> =
        listOf(
            Pair(stringResource(Res.string.songs), SearchItemType.TRACK),
            Pair(stringResource(Res.string.albums), SearchItemType.ALBUM),
            Pair(stringResource(Res.string.playlists), SearchItemType.PLAYLIST),
            Pair(stringResource(Res.string.artists), SearchItemType.ARTIST),
            Pair(stringResource(Res.string.podcasts_and_shows), SearchItemType.SHOW),
        )

    LaunchedEffect(accessToken) {
        onEvent(SearchUiEvent.OnGetAccessToken(accessToken))
    }

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(
            query = uiState.searchQuery,
            onQueryChange = {
                onEvent(SearchUiEvent.OnSearchBarQueryChange(it))
            },
            onSearch = {
                keyboardController?.hide()
                focusManager.clearFocus()
            },
            active = uiState.isSearchBarActive,
            onActiveChange = {
                onEvent(SearchUiEvent.OnSearchBarActiveChange(it))
            },
            modifier =
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 12.dp),
            placeholder = {
                Text(text = stringResource(Res.string.search_bar_placeholder))
            },
            leadingIcon =
                if (uiState.isSearchBarActive) {
                    {
                        IconButton(
                            onClick = {
                                onEvent(SearchUiEvent.OnSearchBarActiveChange(false))
                            },
                        ) {
                            Icon(
                                // TODO("Replace with Icons.AutoMirrored.Filled.ArrowBack once https://github.com/JetBrains/compose-multiplatform/issues/4172 is fixed")
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = null,
                            )
                        }
                    }
                } else {
                    {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                        )
                    }
                },
            trailingIcon =
                if (uiState.isSearchBarActive && uiState.searchQuery.isNotEmpty()) {
                    {
                        IconButton(
                            onClick = {
                                onEvent(SearchUiEvent.OnSearchBarQueryChange(""))
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = null,
                            )
                        }
                    }
                } else {
                    null
                },
        )
        if (uiState.isSearchBarActive) {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                filterItems.forEach { filter ->
                    FilterChip(
                        selected = filter.second == uiState.searchItemType,
                        onClick = {
                            onEvent(SearchUiEvent.OnSearchItemTypeChange(filter.second))
                        },
                        label = {
                            Text(filter.first)
                        },
                    )
                }
            }
            when (val searchResult = uiState.searchResult) {
                is Result.Empty -> {
                    // Nothing is shown
                }

                is Result.Error -> {
                    Box(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp),
                    ) {
                        Text(
                            "${searchResult.message}",
                            modifier = Modifier.align(Alignment.Center),
                        )
                    }
                }

                is Result.Loading -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        // TODO("Uncomment once https://github.com/JetBrains/compose-multiplatform/issues/4157 is fixed")
                        // CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }

                is Result.Success -> {
                    SearchListContent(
                        searchItemType = uiState.searchItemType,
                        albums = uiState.searchResult.data?.albums.orEmpty(),
                        artists = uiState.searchResult.data?.artists.orEmpty(),
                        playlists = uiState.searchResult.data?.playlists.orEmpty(),
                        shows = uiState.searchResult.data?.shows.orEmpty(),
                        tracks = uiState.searchResult.data?.tracks.orEmpty(),
                        // TODO("Add Modifier.imeNestedScroll() when available")
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
        } else {
            when (val categoriesResult = uiState.categoriesResult) {
                is Result.Empty -> {
                    // Nothing is shown
                }

                is Result.Error -> {
                    Box(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp),
                    ) {
                        Text(
                            "${categoriesResult.message}",
                            modifier = Modifier.align(Alignment.Center),
                        )
                    }
                }

                is Result.Loading -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        // TODO("Uncomment once https://github.com/JetBrains/compose-multiplatform/issues/4157 is fixed")
                        // CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }

                is Result.Success -> {
                    val categories = categoriesResult.data.orEmpty()
                    if (categories.isNotEmpty()) {
                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(minSize = 100.dp),
                            modifier = Modifier.fillMaxSize(),
                            contentPadding =
                                PaddingValues(
                                    horizontal = 16.dp,
                                    vertical = 12.dp,
                                ),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            content = {
                                header {
                                    Text(
                                        text = stringResource(Res.string.browse_all),
                                        modifier = Modifier.padding(bottom = 6.dp),
                                        style = MaterialTheme.typography.titleMedium,
                                    )
                                }
                                items(
                                    items = categories,
                                    key = { it.id!! },
                                ) { category ->
                                    CategoriesListItem(
                                        name = category.name.orEmpty(),
                                        imageUrl = category.icon.orEmpty(),
                                        onClick = { /*TODO*/ },
                                    )
                                }
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchListContent(
    searchItemType: SearchItemType,
    albums: List<AlbumsItem>,
    artists: List<Artist>,
    playlists: List<Playlist>,
    shows: List<ShowsItem>,
    tracks: List<Track>,
    modifier: Modifier = Modifier,
) {
    when (searchItemType) {
        SearchItemType.ALBUM -> {
            if (albums.isNotEmpty()) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 160.dp),
                    modifier = modifier,
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    content = {
                        items(
                            items = albums,
                            key = { it.id!! },
                        ) { album ->
                            AlbumsListItem(
                                name = album.name.orEmpty(),
                                imageUrl = album.image.orEmpty(),
                                artists = album.artists,
                                typeAndYear = album.typeAndYear,
                                onClick = { /*TODO*/ },
                            )
                        }
                    },
                )
            }
        }

        SearchItemType.ARTIST -> {
            if (artists.isNotEmpty()) {
                LazyColumn(
                    modifier = modifier,
                    content = {
                        items(
                            items = artists,
                            key = { it.id!! },
                        ) { artist ->
                            ArtistsListItem(
                                name = artist.name.orEmpty(),
                                imageUrl = artist.image.orEmpty(),
                                onClick = { /*TODO*/ },
                            )
                        }
                    },
                )
            }
        }

        SearchItemType.PLAYLIST -> {
            if (playlists.isNotEmpty()) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 160.dp),
                    modifier = modifier,
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    content = {
                        items(
                            items = playlists,
                            key = { it.id!! },
                        ) { playlist ->
                            PlalistsListItem(
                                name = playlist.name.orEmpty(),
                                imageUrl = playlist.image.orEmpty(),
                                owner = playlist.owner,
                                onClick = { /*TODO*/ },
                            )
                        }
                    },
                )
            }
        }

        SearchItemType.TRACK -> {
            if (tracks.isNotEmpty()) {
                LazyColumn(
                    modifier = modifier,
                    content = {
                        items(
                            items = tracks,
                            key = { it.id!! },
                        ) { track ->
                            TracksListItem(
                                name = track.name.orEmpty(),
                                imageUrl = track.image.orEmpty(),
                                artists = track.artistsNames.orEmpty(),
                                onClick = { /*TODO*/ },
                            )
                        }
                    },
                )
            }
        }

        SearchItemType.SHOW -> {
            if (shows.isNotEmpty()) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 160.dp),
                    modifier = modifier,
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    content = {
                        items(
                            items = shows,
                            key = { it.id!! },
                        ) { show ->
                            ShowsListItem(
                                name = show.name.orEmpty(),
                                imageUrl = show.image.orEmpty(),
                                publisher = show.publisher,
                                onClick = { /*TODO*/ },
                            )
                        }
                    },
                )
            }
        }

        else -> {
            // Nothing is shown
        }
    }
}
