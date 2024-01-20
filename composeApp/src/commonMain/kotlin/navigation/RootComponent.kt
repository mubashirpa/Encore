package navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import navigation.RootComponent.Child
import navigation.RootComponent.Child.HomeScreen

interface RootComponent {

    val childStack: Value<ChildStack<*, Child>>

    // It's possible to pop multiple screens at a time on iOS
    fun onBackClicked(toIndex: Int)

    fun onDeepLink(authorizationCode: String?)

    // Defines all possible child components
    sealed class Child {

        class HomeScreen(val component: HomeScreenComponent) : Child()
    }
}

class DefaultRootComponent(
    componentContext: ComponentContext,
    authorizationCode: String? = null
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Configuration>()

    override val childStack: Value<ChildStack<*, Child>> =
        childStack(
            source = navigation,
            serializer = Configuration.serializer(), // Or null to disable navigation state saving
            initialConfiguration = Configuration.HomeScreen(authorizationCode), // The initial child component is HomeScreen
            handleBackButton = true, // Pop the back stack on back button press
            childFactory = ::createChild,
        )

    private fun createChild(
        configuration: Configuration,
        componentContext: ComponentContext
    ): Child =
        when (configuration) {
            is Configuration.HomeScreen -> {
                HomeScreen(homeScreenComponent(componentContext, configuration))
            }
        }

    private fun homeScreenComponent(
        componentContext: ComponentContext,
        configuration: Configuration.HomeScreen
    ): HomeScreenComponent =
        DefaultHomeScreenComponent(
            componentContext = componentContext,
            authCode = configuration.authorizationCode,
        )

    override fun onBackClicked(toIndex: Int) {
        navigation.popTo(index = toIndex)
    }

    override fun onDeepLink(authorizationCode: String?) {
        navigation.replaceAll(Configuration.HomeScreen(authorizationCode = authorizationCode))
    }

    @Serializable // kotlinx-serialization plugin must be applied
    private sealed interface Configuration {

        @Serializable
        data class HomeScreen(val authorizationCode: String?) : Configuration
    }
}