package presentation.search_result

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.search_result.components.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResultScreen() {
    val filterItems = listOf("Top", "Songs", "Playlists", "Albums", "Podcasts & show", "Artists")

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(
            query = "",
            onQueryChange = {},
            onSearch = {},
            modifier =
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp),
            placeholder = {
                Text(text = "What will you listen to?")
            },
            leadingIcon = {
                IconButton(
                    onClick = { /* TODO */ },
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                    )
                }
            },
        )
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
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            content = {},
        )
    }
}
