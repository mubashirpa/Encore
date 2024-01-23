package presentation.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp
import core.Result
import presentation.home.components.HomeGridItem

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun SearchScreen(
    uiState: SearchUiState,
    onEvent: (SearchUiEvent) -> Unit,
    accessToken: String
) {
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(accessToken) {
        onEvent(SearchUiEvent.OnGetAccessToken(accessToken))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .semantics { isTraversalGroup = true }
    ) {
        DockedSearchBar(
            query = text,
            onQueryChange = { text = it },
            onSearch = { active = false },
            active = active,
            onActiveChange = { active = it },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 8.dp)
                .semantics { traversalIndex = -1f },
            placeholder = {
                Text("What will you listen to?")
            },
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null
                )
            }
        ) {
            repeat(4) { idx ->
                val resultText = "Suggestion $idx"
                ListItem(
                    headlineContent = {
                        Text(resultText)
                    },
                    supportingContent = {
                        Text("Additional info")
                    },
                    leadingContent = {
                        Icon(
                            Icons.Filled.Star,
                            contentDescription = null
                        )
                    },
                    modifier = Modifier
                        .clickable {
                            text = resultText
                            active = false
                        }
                        .fillMaxWidth()
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 72.dp)
                .verticalScroll(rememberScrollState())
        ) {
            if (uiState.categoriesResult is Result.Success) {
                val categories = uiState.categoriesResult.data.orEmpty()
                if (categories.isNotEmpty()) {
                    Text(
                        text = "Genres",
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                    FlowRow(
                        modifier = Modifier
                            .wrapContentHeight()
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                bottom = 12.dp
                            )
                            .fillMaxWidth(1f),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        maxItemsInEachRow = 2
                    ) {
                        categories.forEach { category ->
                            HomeGridItem(
                                name = category.name.orEmpty(),
                                imageUrl = category.icons?.firstOrNull()?.url.orEmpty(),
                                modifier = Modifier.weight(1F, fill = true)
                            )
                        }
                    }
                }
            }
        }
    }
}