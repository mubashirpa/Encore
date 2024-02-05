package navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import navigation.RootComponent.Child
import navigation.RootComponent.Child.HomeContainer
import navigation.RootComponent.Child.PlaylistScreen

interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>

    // Defines all possible child components
    sealed class Child {
        class HomeContainer(val component: HomeContainerComponent) : Child()

        class PlaylistScreen(val component: PlaylistScreenComponent) : Child()
    }
}

class DefaultRootComponent(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<Configuration>()

    override val childStack: Value<ChildStack<*, Child>> =
        childStack(
            source = navigation,
            serializer = Configuration.serializer(), // Or null to disable navigation state saving
            initialConfiguration = Configuration.HomeContainer, // The initial child component is HomeContainer
            handleBackButton = true, // Pop the back stack on back button press
            childFactory = ::createChild,
        )

    private fun createChild(
        configuration: Configuration,
        componentContext: ComponentContext,
    ): Child =
        when (configuration) {
            Configuration.HomeContainer -> {
                HomeContainer(homeContainerComponent(componentContext))
            }

            is Configuration.PlaylistScreen -> {
                PlaylistScreen(playlistScreenComponent(componentContext))
            }
        }

    private fun homeContainerComponent(componentContext: ComponentContext): HomeContainerComponent =
        DefaultHomeContainerComponent(
            componentContext = componentContext,
            onNavigateToPlaylistScreen = {
                navigation.push(Configuration.PlaylistScreen(it))
            },
        )

    private fun playlistScreenComponent(componentContext: ComponentContext): PlaylistScreenComponent =
        DefaultPlaylistScreenComponent(componentContext = componentContext)

    @Serializable // kotlinx-serialization plugin must be applied
    private sealed interface Configuration {
        @Serializable
        data object HomeContainer : Configuration

        @Serializable
        data class PlaylistScreen(val playlistId: String) : Configuration
    }
}
