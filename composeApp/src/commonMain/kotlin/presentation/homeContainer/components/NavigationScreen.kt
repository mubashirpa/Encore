package presentation.homeContainer.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.painter.Painter
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import encore.composeapp.generated.resources.Res
import navigation.HomeContainerComponent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
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
