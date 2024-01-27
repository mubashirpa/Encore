package presentation.home_container.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.painter.Painter
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import navigation.HomeContainerComponent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun <T> T.NavigationScreen(
    component: HomeContainerComponent,
    content: @Composable T.(
        isSelected: Boolean,
        icon: Painter,
        // selectedIcon: ImageVector,
        // unselectedIcon: ImageVector,
        // textId: Int,
        text: String,
        onClick: () -> Unit,
    ) -> Unit,
) {
    val stack by component.childStack.subscribeAsState()
    val activeChild = stack.active.instance

    content(
        activeChild is HomeContainerComponent.Child.HomeScreen,
        painterResource("home.xml"),
        "Home",
        component::onHomeScreenTabClicked,
    )
    content(
        activeChild is HomeContainerComponent.Child.SearchScreen,
        painterResource("search.xml"),
        "Search",
        component::onSearchScreenTabClicked,
    )
    content(
        activeChild is HomeContainerComponent.Child.LibraryScreen,
        painterResource("library.xml"),
        "Library",
        component::onLibraryScreenTabClicked,
    )
}
