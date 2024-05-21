package presentation.playlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import core.Result
import domain.model.tracks.Track
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.play
import kotlinproject.composeapp.generated.resources.retry
import kotlinproject.composeapp.generated.resources.shuffle
import org.jetbrains.compose.resources.stringResource
import presentation.playlist.components.PlaylistListItem
import presentation.theme.EncoreDynamicTheme
import presentation.utils.verticalGradientScrim

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaylistScreen(
    uiState: PlaylistUiState,
    onEvent: (PlaylistUiEvent) -> Unit,
    playlistId: String,
    onPlayClicked: (tracks: List<Track>) -> Unit,
    onCloseClicked: () -> Unit,
) {
    val layoutDirection = LocalLayoutDirection.current

    LaunchedEffect(playlistId) {
        onEvent(PlaylistUiEvent.OnGetPlaylistId(playlistId))
    }

    EncoreDynamicTheme(imageUrl = uiState.playlistImageUrl) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            when (val playlistItemsResult = uiState.playlistItemsResult) {
                is Result.Empty -> {
                    // Nothing is shown
                }

                is Result.Error -> {
                    Column(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = playlistItemsResult.message.orEmpty(),
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            onClick = {
                                onEvent(PlaylistUiEvent.OnRetry)
                            },
                        ) {
                            Text(text = stringResource(Res.string.retry))
                        }
                    }
                }

                is Result.Loading -> {
                    Box(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .wrapContentSize(Alignment.Center),
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is Result.Success -> {
                    val playlist = playlistItemsResult.data
                    val playlistItems = playlist?.tracks.orEmpty()

                    if (playlistItems.isNotEmpty()) {
                        LazyColumn(
                            modifier =
                                Modifier
                                    .fillMaxSize()
                                    .verticalGradientScrim(
                                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.50f),
                                        startYPercentage = 1f,
                                        endYPercentage = 0f,
                                    ),
                            contentPadding =
                                PaddingValues(
                                    start = innerPadding.calculateStartPadding(layoutDirection),
                                    end = innerPadding.calculateEndPadding(layoutDirection),
                                    bottom = innerPadding.calculateBottomPadding(),
                                ),
                            content = {
                                item {
                                    TopAppBar(
                                        title = {},
                                        navigationIcon = {
                                            IconButton(onClick = onCloseClicked) {
                                                Icon(
                                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                                    contentDescription = null,
                                                )
                                            }
                                        },
                                        actions = {
                                            IconButton(onClick = { /*TODO*/ }) {
                                                Icon(
                                                    imageVector = Icons.Default.FavoriteBorder,
                                                    contentDescription = null,
                                                )
                                            }
                                            IconButton(onClick = { /*TODO*/ }) {
                                                Icon(
                                                    imageVector = Icons.Default.MoreVert,
                                                    contentDescription = null,
                                                )
                                            }
                                        },
                                        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                                    )
                                    Column(
                                        modifier =
                                            Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 16.dp)
                                                .padding(top = 12.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                    ) {
                                        BoxWithConstraints {
                                            val imageSize =
                                                remember(maxWidth) {
                                                    maxWidth * 2f / 3f
                                                }
                                            KamelImage(
                                                resource = asyncPainterResource(playlist?.image.orEmpty()),
                                                contentDescription = null,
                                                modifier =
                                                    Modifier
                                                        .size(imageSize)
                                                        .clip(MaterialTheme.shapes.medium),
                                                alignment = Alignment.TopCenter,
                                                contentScale = ContentScale.Crop,
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(32.dp))
                                        Text(
                                            text = playlist?.name.orEmpty(),
                                            color = MaterialTheme.colorScheme.onSurface,
                                            fontWeight = FontWeight.Bold,
                                            overflow = TextOverflow.Ellipsis,
                                            maxLines = 1,
                                            style = MaterialTheme.typography.headlineSmall,
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(
                                            text = playlist?.description.orEmpty(),
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            textAlign = TextAlign.Center,
                                            overflow = TextOverflow.Ellipsis,
                                            maxLines = 2,
                                            style = MaterialTheme.typography.bodyMedium,
                                        )
                                    }
                                    Row(
                                        modifier =
                                            Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 32.dp, vertical = 24.dp),
                                        horizontalArrangement = Arrangement.Center,
                                    ) {
                                        ElevatedButton(
                                            onClick = {
                                                onPlayClicked(playlistItems.shuffled())
                                            },
                                            modifier = Modifier.weight(1f),
                                            contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
                                        ) {
                                            Icon(
                                                imageVector = Icons.Filled.Shuffle,
                                                contentDescription = null,
                                                modifier = Modifier.size(ButtonDefaults.IconSize),
                                            )
                                            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                                            Text(text = stringResource(Res.string.shuffle))
                                        }
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Button(
                                            onClick = {
                                                onPlayClicked(playlistItems)
                                            },
                                            modifier = Modifier.weight(1f),
                                            contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
                                        ) {
                                            Icon(
                                                imageVector = Icons.Filled.PlayArrow,
                                                contentDescription = null,
                                                modifier = Modifier.size(ButtonDefaults.IconSize),
                                            )
                                            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                                            Text(text = stringResource(Res.string.play))
                                        }
                                    }
                                }

                                items(
                                    items = playlistItems,
                                    key = { it.id!! },
                                ) { playlistItem ->
                                    PlaylistListItem(
                                        name = playlistItem.formattedName.orEmpty(),
                                        imageUrl = playlistItem.image.orEmpty(),
                                        artists = playlistItem.artistsNames.orEmpty(),
                                        onClick = {
                                            onPlayClicked(listOf(playlistItem))
                                        },
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
