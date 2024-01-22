package navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import navigation.HomeContainerComponent.Child
import navigation.HomeContainerComponent.Child.HomeScreen
import navigation.HomeContainerComponent.Child.LibraryScreen
import navigation.HomeContainerComponent.Child.SearchScreen

interface HomeContainerComponent {

    val childStack: Value<ChildStack<*, Child>>

    fun onHomeScreenTabClicked()
    fun onLibraryScreenTabClicked()
    fun onSearchScreenTabClicked()

    sealed class Child {

        class HomeScreen(val component: HomeScreenComponent) : Child()
        class LibraryScreen(val component: LibraryScreenComponent) : Child()
        class SearchScreen(val component: SearchScreenComponent) : Child()
    }
}

class DefaultHomeContainerComponent(
    componentContext: ComponentContext
) : HomeContainerComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Configuration>()

    override val childStack: Value<ChildStack<*, Child>> =
        childStack(
            source = navigation,
            serializer = Configuration.serializer(),
            initialConfiguration = Configuration.HomeScreen,
            handleBackButton = true,
            childFactory = ::createChild
        )

    private fun createChild(
        configuration: Configuration,
        componentContext: ComponentContext
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

    override fun onHomeScreenTabClicked() {
        navigation.bringToFront(Configuration.HomeScreen)
    }

    override fun onLibraryScreenTabClicked() {
        navigation.bringToFront(Configuration.LibraryScreen)
    }

    override fun onSearchScreenTabClicked() {
        navigation.bringToFront(Configuration.SearchScreen)
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