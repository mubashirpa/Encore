package presentation.playlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import core.Result
import domain.model.tracks.Track
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import presentation.playlist.components.PlaylistListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaylistScreen(
    uiState: PlaylistUiState,
    onEvent: (PlaylistUiEvent) -> Unit,
    playlistId: String,
    onPlaylistItemClicked: (track: Track) -> Unit,
    onCloseClicked: () -> Unit,
) {
    val layoutDirection = LocalLayoutDirection.current

    LaunchedEffect(playlistId) {
        onEvent(PlaylistUiEvent.OnGetPlaylistId(playlistId))
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        if (uiState.playlistItemsResult is Result.Success) {
            val playlist = uiState.playlistItemsResult.data
            val playlistItems = playlist?.tracks.orEmpty()
            if (playlistItems.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding =
                        PaddingValues(
                            start = innerPadding.calculateStartPadding(layoutDirection),
                            end = innerPadding.calculateEndPadding(layoutDirection),
                            bottom = innerPadding.calculateBottomPadding(),
                        ),
                    content = {
                        item {
                            val colors =
                                listOf(
                                    MaterialTheme.colorScheme.surface.copy(0.1f),
                                    MaterialTheme.colorScheme.surface.copy(0.3f),
                                    MaterialTheme.colorScheme.surface,
                                )

                            Card(
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1f),
                                shape = RectangleShape,
                            ) {
                                Box {
                                    KamelImage(
                                        resource = asyncPainterResource(playlist?.image.orEmpty()),
                                        contentDescription = null,
                                        modifier = Modifier.fillMaxSize(),
                                        alignment = Alignment.TopCenter,
                                        contentScale = ContentScale.Crop,
                                    )
                                    Box(
                                        modifier =
                                            Modifier
                                                .fillMaxSize()
                                                .background(Brush.verticalGradient(colors)),
                                    )
                                    Column(
                                        modifier =
                                            Modifier
                                                .align(Alignment.BottomStart)
                                                .padding(horizontal = 16.dp, vertical = 12.dp),
                                        verticalArrangement = Arrangement.spacedBy(6.dp),
                                    ) {
                                        Text(
                                            text = playlist?.name.orEmpty(),
                                            color = MaterialTheme.colorScheme.onSurface,
                                            fontWeight = FontWeight.Bold,
                                            overflow = TextOverflow.Ellipsis,
                                            maxLines = 2,
                                            style = MaterialTheme.typography.headlineMedium,
                                        )
                                        Text(
                                            text = playlist?.description.orEmpty(),
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            overflow = TextOverflow.Ellipsis,
                                            maxLines = 1,
                                            style = MaterialTheme.typography.bodyLarge,
                                        )
                                    }
                                    TopAppBar(
                                        title = {},
                                        navigationIcon = {
                                            FilledIconButton(
                                                onClick = onCloseClicked,
                                                colors =
                                                    IconButtonDefaults.iconButtonColors(
                                                        containerColor = MaterialTheme.colorScheme.surface,
                                                        contentColor = MaterialTheme.colorScheme.onSurface,
                                                    ),
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.ArrowBack,
                                                    contentDescription = null,
                                                )
                                            }
                                        },
                                        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                                    )
                                }
                            }
                            Row(
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp, vertical = 12.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                IconButton(
                                    onClick = { /*TODO*/ },
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.FavoriteBorder,
                                        contentDescription = null,
                                    )
                                }
                                IconButton(
                                    onClick = { /*TODO*/ },
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Share,
                                        contentDescription = null,
                                    )
                                }
                                IconButton(
                                    onClick = { /*TODO*/ },
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.MoreVert,
                                        contentDescription = null,
                                    )
                                }
                                Spacer(modifier = Modifier.weight(1f))
                                FloatingActionButton(
                                    onClick = { /*TODO*/ },
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.PlayArrow,
                                        contentDescription = null,
                                    )
                                }
                            }
                        }

                        items(
                            items = playlistItems,
                            key = { it.id!! },
                        ) { playlistItem ->
                            PlaylistListItem(
                                name = playlistItem.name.orEmpty(),
                                imageUrl = playlistItem.image.orEmpty(),
                                artists = playlistItem.artistsNames.orEmpty(),
                                onClick = {
                                    onPlaylistItemClicked(playlistItem)
                                },
                            )
                        }
                    },
                )
            }
        }
    }
}
