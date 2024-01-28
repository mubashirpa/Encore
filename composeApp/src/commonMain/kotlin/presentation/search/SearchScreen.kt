package presentation.search

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.unit.dp
import core.Result
import presentation.search.components.CategoriesListItem
import presentation.search.components.SearchBar
import presentation.search.components.SearchListItem

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun SearchScreen(
    uiState: SearchUiState,
    onEvent: (SearchUiEvent) -> Unit,
    accessToken: String,
) {
    val filterItems = listOf("Top", "Songs", "Playlists", "Albums", "Podcasts & show", "Artists")

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
                onEvent(SearchUiEvent.SearchForItem)
            },
            active = uiState.isSearchBarActive,
            onActiveChange = {
                onEvent(SearchUiEvent.OnSearchBarActiveChange(it))
            },
            modifier =
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp),
            placeholder = {
                Text(text = "What will you listen to?")
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
                                imageVector = Icons.Default.ArrowBack,
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
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                filterItems.forEachIndexed { index, text ->
                    FilterChip(
                        selected = index == 0,
                        onClick = {},
                        label = {
                            Text(text)
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
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }

                is Result.Success -> {
                    val tracks = searchResult.data?.tracks?.items.orEmpty()
                    if (tracks.isNotEmpty()) {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            content = {
                                items(
                                    items = tracks,
                                    key = { "${it.id}" },
                                ) { searchItem ->
                                    SearchListItem(
                                        title = "${searchItem.name}",
                                        subtitle =
                                            searchItem.artists?.joinToString(", ") {
                                                it.name.toString()
                                            }.orEmpty(),
                                        imageUrl = searchItem.album?.images?.firstOrNull()?.url.orEmpty(),
                                        onClick = { /*TODO*/ },
                                    )
                                }
                            },
                        )
                    }
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
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }

                is Result.Success -> {
                    val categories = categoriesResult.data.orEmpty()
                    if (categories.isNotEmpty()) {
                        Text(
                            text = "Browse all",
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                            style = MaterialTheme.typography.titleMedium,
                        )
                        FlowRow(
                            modifier =
                                Modifier
                                    .wrapContentHeight()
                                    .padding(
                                        start = 16.dp,
                                        end = 16.dp,
                                        bottom = 12.dp,
                                    )
                                    .fillMaxWidth(1f)
                                    .verticalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            maxItemsInEachRow = 2,
                        ) {
                            categories.forEach { category ->
                                CategoriesListItem(
                                    name = category.name.orEmpty(),
                                    imageUrl = category.icons?.firstOrNull()?.url.orEmpty(),
                                    modifier = Modifier.weight(1F, fill = true),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
