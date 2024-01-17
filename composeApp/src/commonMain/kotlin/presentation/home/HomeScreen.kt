package presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.Result
import presentation.home.components.HomeListItem

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onEvent: (HomeUiEvent) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Button(
            onClick = {
                onEvent(HomeUiEvent.GetCategories)
            }
        ) {
            Text("Click me!")
        }
        if (uiState.featuredPlaylistsResult is Result.Success) {
            Text(
                text = "Popular",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleMedium
            )
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                content = {
                    items(
                        items = uiState.featuredPlaylistsResult.data.orEmpty(),
                        key = { it.id!! }
                    ) {
                        HomeListItem(
                            name = it.name.orEmpty(),
                            imageUrl = it.images?.firstOrNull()?.url.orEmpty()
                        )
                    }
                }
            )
        }
    }
}