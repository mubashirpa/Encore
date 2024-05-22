package presentation.homeContainer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import encore.composeapp.generated.resources.Res
import encore.composeapp.generated.resources.ic_add
import encore.composeapp.generated.resources.ic_refresh
import encore.composeapp.generated.resources.ic_search
import encore.composeapp.generated.resources.ic_shazam
import encore.composeapp.generated.resources.title_home_screen
import encore.composeapp.generated.resources.title_library_screen
import encore.composeapp.generated.resources.title_search_screen
import navigation.HomeContainerComponent
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import presentation.home.HomeScreen
import presentation.homeContainer.components.HomeAppBar
import presentation.homeContainer.components.HomeBottomBar
import presentation.library.LibraryScreen
import presentation.search.SearchScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContainer(component: HomeContainerComponent) {
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
            state = rememberTopAppBarState(),
        )
    val uiState = component.uiState.subscribeAsState()
    val accessToken = uiState.value.accessToken
    val profileUrl = uiState.value.currentUsersProfile?.image

    Scaffold(
        modifier =
            Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .imePadding(),
        bottomBar = {
            HomeBottomBar(component)
        },
        contentWindowInsets = WindowInsets.safeDrawing.exclude(WindowInsets.statusBars),
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
                            title = stringResource(Res.string.title_home_screen),
                            profileImage = "$profileUrl",
                            actions = {
                                IconButton(onClick = { /* do something */ }) {
                                    Icon(
                                        painter = painterResource(Res.drawable.ic_refresh),
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
                            navigateToPlaylistScreen = component::navigateToPlaylistScreen,
                        )
                    }

                    is HomeContainerComponent.Child.LibraryScreen -> {
                        val libraryScreenComponent = instance.component
                        val viewModel = libraryScreenComponent.viewModel
                        HomeAppBar(
                            title = stringResource(Res.string.title_library_screen),
                            profileImage = "$profileUrl",
                            actions = {
                                IconButton(onClick = { /* do something */ }) {
                                    Icon(
                                        painter = painterResource(Res.drawable.ic_search),
                                        contentDescription = null,
                                    )
                                }
                                IconButton(onClick = { /* do something */ }) {
                                    Icon(
                                        painter = painterResource(Res.drawable.ic_add),
                                        contentDescription = null,
                                    )
                                }
                            },
                            scrollBehavior = scrollBehavior,
                        )
                        LibraryScreen(
                            uiState = viewModel.uiState,
                            onEvent = viewModel::onEvent,
                            accessToken = accessToken,
                        )
                    }

                    is HomeContainerComponent.Child.SearchScreen -> {
                        val searchScreenComponent = instance.component
                        val viewModel = searchScreenComponent.viewModel

                        HomeAppBar(
                            title = stringResource(Res.string.title_search_screen),
                            profileImage = "$profileUrl",
                            actions = {
                                IconButton(onClick = { /* do something */ }) {
                                    Icon(
                                        painter = painterResource(Res.drawable.ic_shazam),
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
                            updateBackCallback = searchScreenComponent::updateBackCallback,
                        )
                    }
                }
            }
        }
    }
}
