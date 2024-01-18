import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import navigation.DefaultRootComponent
import presentation.App

fun MainViewController() = ComposeUIViewController {
    val rootComponent = remember {
        DefaultRootComponent(DefaultComponentContext(LifecycleRegistry()))
    }
    App(
        component = rootComponent,
        modifier = Modifier.fillMaxSize()
    )
}