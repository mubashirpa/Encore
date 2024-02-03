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
        painterResource(Res.drawable.home),
        stringResource(Res.string.label_home),
        component::onHomeScreenTabClicked,
    )
    content(
        activeChild is HomeContainerComponent.Child.SearchScreen,
        painterResource(Res.drawable.search),
        stringResource(Res.string.label_search),
        component::onSearchScreenTabClicked,
    )
    content(
        activeChild is HomeContainerComponent.Child.LibraryScreen,
        painterResource(Res.drawable.library),
        stringResource(Res.string.label_library),
        component::onLibraryScreenTabClicked,
    )
}
