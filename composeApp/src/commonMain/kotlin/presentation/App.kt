package presentation

import androidx.compose.runtime.Composable
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import presentation.home.HomeScreen
import presentation.home.HomeViewModel
import presentation.theme.EncoreTheme

@Composable
fun App() {
    KoinContext {
        EncoreTheme {
            val homeViewModel: HomeViewModel = koinInject()

            HomeScreen(
                uiState = homeViewModel.uiState,
                onEvent = homeViewModel::onEvent
            )
        }
    }
}