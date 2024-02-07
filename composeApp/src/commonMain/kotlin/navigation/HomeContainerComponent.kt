package navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import core.Result
import domain.model.spotify.users.CurrentUsersProfile
import domain.usecase.spotify.accessToken.GetAccessTokenUseCase
import domain.usecase.spotify.users.GetCurrentUsersProfileUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import navigation.HomeContainerComponent.Child
import navigation.HomeContainerComponent.Child.HomeScreen
import navigation.HomeContainerComponent.Child.LibraryScreen
import navigation.HomeContainerComponent.Child.SearchScreen
import navigation.HomeContainerComponent.HomeContainerUiState
import navigation.utils.asValue
import navigation.utils.coroutineScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface HomeContainerComponent {
    val childStack: Value<ChildStack<*, Child>>
    val uiState: Value<HomeContainerUiState>

    fun onHomeScreenTabClicked()

    fun onLibraryScreenTabClicked()

    fun onSearchScreenTabClicked()

    fun navigateToPlaylistScreen(playlistId: String)

    data class HomeContainerUiState(
        val accessToken: String = "",
        val currentUsersProfile: CurrentUsersProfile? = null,
    )

    sealed class Child {
        class HomeScreen(val component: HomeScreenComponent) : Child()

        class LibraryScreen(val component: LibraryScreenComponent) : Child()

        class SearchScreen(val component: SearchScreenComponent) : Child()
    }
}

class DefaultHomeContainerComponent(
    componentContext: ComponentContext,
    private val onNavigateToPlaylistScreen: (playlistId: String) -> Unit,
) : HomeContainerComponent, ComponentContext by componentContext, KoinComponent {
    private val navigation = StackNavigation<Configuration>()

    private val coroutineScope = coroutineScope()
    private val channel = Channel<HomeContainerUiState>()
    private val getAccessTokenUseCase: GetAccessTokenUseCase by inject()
    private val getCurrentUsersProfileUseCase: GetCurrentUsersProfileUseCase by inject()

    override val childStack: Value<ChildStack<*, Child>> =
        childStack(
            source = navigation,
            serializer = Configuration.serializer(),
            initialConfiguration = Configuration.HomeScreen,
            handleBackButton = true,
            childFactory = ::createChild,
        )

    init {
        getAccessToken()
    }

    override val uiState: Value<HomeContainerUiState> =
        flow {
            for (uiState in channel) {
                emit(uiState)
            }
        }.asValue(initialValue = HomeContainerUiState(), lifecycle = lifecycle)

    override fun onHomeScreenTabClicked() {
        navigation.bringToFront(Configuration.HomeScreen)
    }

    override fun onLibraryScreenTabClicked() {
        navigation.bringToFront(Configuration.LibraryScreen)
    }

    override fun onSearchScreenTabClicked() {
        navigation.bringToFront(Configuration.SearchScreen)
    }

    override fun navigateToPlaylistScreen(playlistId: String) {
        onNavigateToPlaylistScreen(playlistId)
    }

    private fun createChild(
        configuration: Configuration,
        componentContext: ComponentContext,
    ): Child =
        when (configuration) {
            is Configuration.HomeScreen -> {
                HomeScreen(homeScreenComponent(componentContext))
            }

            is Configuration.LibraryScreen -> {
                LibraryScreen(libraryScreenComponent(componentContext))
            }

            is Configuration.SearchScreen -> {
                SearchScreen(searchScreenComponent(componentContext))
            }
        }

    private fun homeScreenComponent(componentContext: ComponentContext): HomeScreenComponent =
        DefaultHomeScreenComponent(componentContext = componentContext)

    private fun libraryScreenComponent(componentContext: ComponentContext): DefaultLibraryScreenComponent =
        DefaultLibraryScreenComponent(componentContext = componentContext)

    private fun searchScreenComponent(componentContext: ComponentContext): SearchScreenComponent =
        DefaultSearchScreenComponent(componentContext = componentContext)

    private fun getAccessToken() {
        coroutineScope.launch {
            getAccessTokenUseCase().collectLatest {
                val accessToken = it.accessToken
                if (!accessToken.isNullOrEmpty()) {
                    channel.send(uiState.value.copy(accessToken = accessToken))
                    getCurrentUsersProfile(accessToken)
                }
            }
        }
    }

    private fun getCurrentUsersProfile(accessToken: String) {
        getCurrentUsersProfileUseCase(accessToken).onEach { result ->
            if (result is Result.Success) {
                channel.send(uiState.value.copy(currentUsersProfile = result.data))
            }
        }.launchIn(coroutineScope)
    }

    @Serializable
    private sealed interface Configuration {
        @Serializable
        data object HomeScreen : Configuration

        @Serializable
        data object LibraryScreen : Configuration

        @Serializable
        data object SearchScreen : Configuration
    }
}
