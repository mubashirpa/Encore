package presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import presentation.home.HomeScreen
import presentation.home.HomeViewModel

@Composable
fun App() {
    KoinContext {
        MaterialTheme {
            val homeViewModel: HomeViewModel = koinInject()

            HomeScreen(
                uiState = homeViewModel.uiState,
                onEvent = homeViewModel::onEvent
            )
        }
    }
}