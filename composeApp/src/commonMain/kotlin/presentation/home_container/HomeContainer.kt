package presentation.home_container

import androidx.compose.foundation.layout.Column
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
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
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
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
            state = rememberTopAppBarState(),
        )
    val uiState = component.uiState.subscribeAsState()
    val accessToken = uiState.value.accessToken
    val profileUrl = uiState.value.currentUsersProfile?.images?.firstOrNull()?.url

    Scaffold(
        modifier =
            Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
        bottomBar = {
            HomeBottomBar(component)
        },
    ) { innerPadding ->
        Children(
            stack = component.childStack,
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            animation = stackAnimation(fade()),
        ) { child ->
            Column(modifier = Modifier.fillMaxSize()) {
                when (val instance = child.instance) {
                    is HomeContainerComponent.Child.HomeScreen -> {
                        val homeScreenComponent = instance.component
                        val viewModel = homeScreenComponent.viewModel

                        HomeAppBar(
                            title = "Go listen!",
                            profileImage = "$profileUrl",
                            actions = {
                                IconButton(onClick = { /* do something */ }) {
                                    Icon(
                                        painter = painterResource("refresh.xml"),
                                        contentDescription = null,
                                    )
                                }
                            },
                            scrollBehavior = scrollBehavior,
                        )
                        HomeScreen(
                            uiState = viewModel.uiState,
                            onEvent = viewModel::onEvent,
                            accessToken = accessToken,
                        )
                    }

                    is HomeContainerComponent.Child.LibraryScreen -> {
                        HomeAppBar(
                            title = "Your library",
                            profileImage = "$profileUrl",
                            actions = {
                                IconButton(onClick = { /* do something */ }) {
                                    Icon(
                                        painter = painterResource("search.xml"),
                                        contentDescription = null,
                                    )
                                }
                                IconButton(onClick = { /* do something */ }) {
                                    Icon(
                                        painter = painterResource("add.xml"),
                                        contentDescription = null,
                                    )
                                }
                            },
                            scrollBehavior = scrollBehavior,
                        )
                        LibraryScreen()
                    }

                    is HomeContainerComponent.Child.SearchScreen -> {
                        val searchScreenComponent = instance.component
                        val viewModel = searchScreenComponent.viewModel

                        HomeAppBar(
                            title = "Let's search",
                            profileImage = "$profileUrl",
                            actions = {
                                IconButton(onClick = { /* do something */ }) {
                                    Icon(
                                        painter = painterResource("shazam.xml"),
                                        contentDescription = null,
                                    )
                                }
                            },
                            scrollBehavior = scrollBehavior,
                        )
                        SearchScreen(
                            uiState = viewModel.uiState,
                            onEvent = viewModel::onEvent,
                            accessToken = accessToken,
                        )
                    }
                }
            }
        }
    }
}
