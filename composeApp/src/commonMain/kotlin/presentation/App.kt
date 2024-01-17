package presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import navigation.RootComponent
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import presentation.home.HomeScreen
import presentation.home.HomeViewModel
import presentation.theme.EncoreTheme

@Composable
fun App(component: RootComponent, modifier: Modifier = Modifier) {
    KoinContext {
        EncoreTheme {
            val homeViewModel: HomeViewModel = koinInject()

            Children(
                stack = component.childStack,
                modifier = modifier,
                animation = stackAnimation(slide())
            ) { child ->
                when (val instance = child.instance) {
                    is RootComponent.Child.HomeScreen -> {
                        HomeScreen(
                            component = instance.component,
                            uiState = homeViewModel.uiState,
                            onEvent = homeViewModel::onEvent
                        )
                    }
                }
            }
        }
    }
}