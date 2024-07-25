package presentation.player

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import core.Result
import domain.model.tracks.Track
import encore.composeapp.generated.resources.Res
import encore.composeapp.generated.resources.retry
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import player.MediaPlayer
import player.MediaPlayerFactory
import player.PlaybackState
import player.PlayerState
import player.rememberPlayerState
import presentation.theme.EncoreDynamicTheme
import presentation.utils.verticalGradientScrim

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerScreen(
    uiState: PlayerUiState,
    onEvent: (PlayerUiEvent) -> Unit,
    tracks: List<Track>,
) {
    val mediaPlayerFactory: MediaPlayerFactory = koinInject()
    val playerState = rememberPlayerState()
    val mediaPlayer =
        remember {
            mediaPlayerFactory.createMediaPlayer(playerState)
        }

    val selectedIndex = remember { mutableStateOf(0) }
    val isLoading = remember { mutableStateOf(true) }

    // The index was not getting reset
    LaunchedEffect(tracks) { selectedIndex.value = 0 }

    LaunchedEffect(selectedIndex) {
        tracks[selectedIndex.value].id?.let { selectedTrackId ->
            onEvent(PlayerUiEvent.OnGetTrackId(selectedTrackId))
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
    ) {
        EncoreDynamicTheme(imageUrl = uiState.trackImageUrl) {
            when (val trackResult = uiState.trackResult) {
                is Result.Empty -> {
                    // Nothing is shown
                }

                is Result.Error -> {
                    Column(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = trackResult.message.orEmpty(),
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            onClick = { /*TODO*/ },
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
                    Column(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .verticalGradientScrim(
                                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.50f),
                                    startYPercentage = 1f,
                                    endYPercentage = 0f,
                                ).verticalScroll(rememberScrollState()),
                    ) {
                        val track = trackResult.data
                        if (track != null) {
                            playTrack(
                                selectedTrack = track,
                                mediaPlayer = mediaPlayer,
                                isLoading = isLoading,
                                selectedIndex = selectedIndex,
                                tracks = tracks,
                            )

                            TopAppBar(
                                title = {},
                                navigationIcon = {
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                            contentDescription = null,
                                        )
                                    }
                                },
                                actions = {
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
                                        .fillMaxSize()
                                        .padding(horizontal = 32.dp, vertical = 12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                KamelImage(
                                    resource = asyncPainterResource(track.image.orEmpty()),
                                    contentDescription = null,
                                    modifier =
                                        Modifier
                                            .fillMaxWidth()
                                            .aspectRatio(1f)
                                            .clip(MaterialTheme.shapes.medium),
                                    contentScale = ContentScale.Crop,
                                )
                                Spacer(modifier = Modifier.height(32.dp))
                                Text(
                                    text = track.formattedName.orEmpty(),
                                    modifier = Modifier.basicMarquee(),
                                    maxLines = 1,
                                    style = MaterialTheme.typography.headlineSmall,
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant) {
                                    Text(
                                        text = track.artistsNames.orEmpty(),
                                        modifier = Modifier.basicMarquee(),
                                        maxLines = 1,
                                        style = MaterialTheme.typography.bodyMedium,
                                    )
                                }
                                Spacer(modifier = Modifier.height(32.dp))
                                PlayerController(
                                    mediaPlayer = mediaPlayer,
                                    playerState = playerState,
                                    modifier =
                                        Modifier
                                            .padding(vertical = 10.dp)
                                            .align(Alignment.CenterHorizontally),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PlayerController(
    mediaPlayer: MediaPlayer,
    playerState: PlayerState,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.Shuffle,
                contentDescription = null,
            )
        }
        FilledIconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.size(40.dp),
            shape = MaterialTheme.shapes.medium,
        ) {
            Icon(
                imageVector = Icons.Default.SkipPrevious,
                contentDescription = null,
            )
        }
        PlayPauseButton(
            mediaPlayer = mediaPlayer,
            playerState = playerState,
        )
        FilledIconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.size(40.dp),
            shape = MaterialTheme.shapes.medium,
        ) {
            Icon(
                imageVector = Icons.Default.SkipNext,
                contentDescription = null,
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.Timer,
                contentDescription = null,
            )
        }
    }
}

@Composable
private fun PlayPauseButton(
    mediaPlayer: MediaPlayer,
    playerState: PlayerState,
) {
    val icon =
        if (playerState.isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow

    FilledTonalIconButton(
        onClick = {
            if (playerState.isPlaying) {
                mediaPlayer.pause()
            } else {
                mediaPlayer.play()
            }
        },
        modifier = Modifier.size(56.dp),
        shape = MaterialTheme.shapes.large,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
        )
    }
}

private fun playTrack(
    selectedTrack: Track,
    mediaPlayer: MediaPlayer,
    isLoading: MutableState<Boolean>,
    selectedIndex: MutableState<Int>,
    tracks: List<Track>,
) {
    selectedTrack.decryptedMediaUrl?.let { mediaUrl ->
        mediaPlayer.onPlaybackStateChanged { playbackState ->
            when (playbackState) {
                PlaybackState.STATE_IDLE -> {}

                PlaybackState.STATE_BUFFERING -> {
                    isLoading.value = true
                }

                PlaybackState.STATE_READY -> {
                    isLoading.value = false
                }

                PlaybackState.STATE_ENDED -> {
                    if (selectedIndex.value < tracks.size - 1) {
                        selectedIndex.value += 1
                    }
                }
            }
        }
        mediaPlayer.onPlayerError {
            if (selectedIndex.value < tracks.size - 1) {
                selectedIndex.value += 1
            }
        }
        mediaPlayer.play(mediaUrl)
    } ?: run {
        if (selectedIndex.value < tracks.size - 1) {
            selectedIndex.value += 1
        } else {
            // selectedIndex.value = 0
        }
    }
}
