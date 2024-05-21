package presentation.homeContainer.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.painter.Painter
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.home
import kotlinproject.composeapp.generated.resources.ic_home
import kotlinproject.composeapp.generated.resources.ic_library
import kotlinproject.composeapp.generated.resources.ic_search
import kotlinproject.composeapp.generated.resources.library
import kotlinproject.composeapp.generated.resources.search
import navigation.HomeContainerComponent
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun <T> T.NavigationScreen(
    component: HomeContainerComponent,
    content: @Composable T.(
        selected: Boolean,
        icon: Painter,
        label: String,
        onClick: () -> Unit,
    ) -> Unit,
) {
    val stack by component.childStack.subscribeAsState()
    val activeChild = stack.active.instance

    content(
        activeChild is HomeContainerComponent.Child.HomeScreen,
        painterResource(Res.drawable.ic_home),
        stringResource(Res.string.home),
        component::onHomeScreenTabClicked,
    )
    content(
        activeChild is HomeContainerComponent.Child.SearchScreen,
        painterResource(Res.drawable.ic_search),
        stringResource(Res.string.search),
        component::onSearchScreenTabClicked,
    )
    content(
        activeChild is HomeContainerComponent.Child.LibraryScreen,
        painterResource(Res.drawable.ic_library),
        stringResource(Res.string.library),
        component::onLibraryScreenTabClicked,
    )
}
