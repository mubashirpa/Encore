package presentation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import navigation.AppComponent
import org.koin.compose.KoinContext
import presentation.homeContainer.HomeContainer
import presentation.player.PlayerScreen
import presentation.playlist.PlaylistScreen
import presentation.theme.EncoreTheme

@Composable
fun App(
    component: AppComponent,
    modifier: Modifier = Modifier,
) {
    KoinContext {
        EncoreTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = modifier,
                color = MaterialTheme.colorScheme.background,
            ) {
                EncoreApp(
                    component = component,
                    modifier = modifier,
                )
            }
        }
    }
}

@Composable
private fun EncoreApp(
    component: AppComponent,
    modifier: Modifier = Modifier,
) {
    val playerSlot by component.player.subscribeAsState()

    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(0),
    ) { innerPadding ->
        Children(
            stack = component.childStack,
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .consumeWindowInsets(innerPadding)
                    .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)),
            animation = stackAnimation(slide()),
        ) { child ->
            when (val instance = child.instance) {
                is AppComponent.Child.HomeContainer -> {
                    HomeContainer(component = instance.component)
                }

                is AppComponent.Child.PlaylistScreen -> {
                    val playlistScreenComponent = instance.component
                    val viewModel = playlistScreenComponent.viewModel
                    PlaylistScreen(
                        uiState = viewModel.uiState,
                        onEvent = viewModel::onEvent,
                        playlistId = playlistScreenComponent.playlistId,
                        onPlayClicked = playlistScreenComponent::onPlayClicked,
                        onCloseClicked = playlistScreenComponent::onCloseClicked,
                    )
                }
            }
        }
        playerSlot.child?.instance?.also { playerComponent ->
            val viewModel = playerComponent.viewModel
            PlayerScreen(
                uiState = viewModel.uiState,
                onEvent = viewModel::onEvent,
                tracks = playerComponent.tracks,
            )
        }
    }
}
