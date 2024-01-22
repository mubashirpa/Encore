package presentation.home_container

import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import navigation.HomeContainerComponent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.home.HomeScreen
import presentation.home_container.components.HomeAppBar
import presentation.home_container.components.HomeBottomBar
import presentation.library.LibraryScreen
import presentation.search.SearchScreen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun HomeContainer(component: HomeContainerComponent) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        state = rememberTopAppBarState()
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            HomeAppBar(
                title = "Go listen!",
                profileImage = "",
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            painter = painterResource("refresh.xml"),
                            contentDescription = null
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            HomeBottomBar(component)
        }
    ) { innerPadding ->
        Children(
            stack = component.childStack,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            animation = stackAnimation(fade())
        ) { child ->
            when (val instance = child.instance) {
                is HomeContainerComponent.Child.HomeScreen -> {
                    val homeScreenComponent = instance.component
                    HomeScreen(uiState = homeScreenComponent.viewModel.uiState)
                }

                is HomeContainerComponent.Child.LibraryScreen -> {
                    LibraryScreen()
                }

                is HomeContainerComponent.Child.SearchScreen -> {
                    SearchScreen()
                }
            }
        }
    }
}