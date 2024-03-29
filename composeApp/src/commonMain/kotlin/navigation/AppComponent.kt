package navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import domain.model.tracks.Track
import kotlinx.serialization.Serializable
import navigation.AppComponent.Child
import navigation.AppComponent.Child.HomeContainer
import navigation.AppComponent.Child.PlaylistScreen

interface AppComponent {
    val childStack: Value<ChildStack<*, Child>>
    val player: Value<ChildSlot<*, PlayerComponent>>

    // Defines all possible child components
    sealed class Child {
        class HomeContainer(val component: HomeContainerComponent) : Child()

        class PlaylistScreen(val component: PlaylistScreenComponent) : Child()
    }
}

class DefaultAppComponent(
    componentContext: ComponentContext,
) : AppComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<Configuration>()

    override val childStack: Value<ChildStack<*, Child>> =
        childStack(
            source = navigation,
            // Or null to disable navigation state saving
            serializer = Configuration.serializer(),
            // The initial child component is HomeContainer
            initialConfiguration = Configuration.HomeContainer,
            // Pop the back stack on back button press
            handleBackButton = true,
            childFactory = ::createChild,
        )

    private val playerNavigation = SlotNavigation<PlayerConfig>()

    override val player: Value<ChildSlot<*, PlayerComponent>> =
        childSlot(
            source = playerNavigation,
            // Or null to disable navigation state saving
            serializer = PlayerConfig.serializer(),
            // Close the dialog on back button press
            handleBackButton = true,
        ) { config, childComponentContext ->
            DefaultPlayerComponent(
                componentContext = childComponentContext,
                tracks = config.tracks,
                onDismissed = playerNavigation::dismiss,
            )
        }

    private fun createChild(
        configuration: Configuration,
        componentContext: ComponentContext,
    ): Child =
        when (configuration) {
            Configuration.HomeContainer -> {
                HomeContainer(homeContainerComponent(componentContext))
            }

            is Configuration.PlaylistScreen -> {
                PlaylistScreen(playlistScreenComponent(componentContext, configuration))
            }
        }

    private fun homeContainerComponent(componentContext: ComponentContext): HomeContainerComponent =
        DefaultHomeContainerComponent(
            componentContext = componentContext,
            onNavigateToPlaylistScreen = {
                navigation.push(Configuration.PlaylistScreen(it))
            },
        )

    private fun playlistScreenComponent(
        componentContext: ComponentContext,
        configuration: Configuration.PlaylistScreen,
    ): PlaylistScreenComponent =
        DefaultPlaylistScreenComponent(
            componentContext = componentContext,
            playlistId = configuration.playlistId,
            onFinished = navigation::pop,
            onPlayTrack = {
                showPlayer(it)
            },
        )

    private fun showPlayer(tracks: List<Track>) {
        playerNavigation.activate(PlayerConfig(tracks))
    }

    @Serializable // kotlinx-serialization plugin must be applied
    private sealed interface Configuration {
        @Serializable
        data object HomeContainer : Configuration

        @Serializable
        data class PlaylistScreen(val playlistId: String) : Configuration
    }

    @Serializable // kotlinx-serialization plugin must be applied
    private data class PlayerConfig(
        val tracks: List<Track>,
    )
}
