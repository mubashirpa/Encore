import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import io.github.aakira.napier.Napier
import navigation.DefaultRootComponent
import presentation.App

fun MainViewController() = ComposeUIViewController {
    val rootComponent = remember {
        DefaultRootComponent(DefaultComponentContext(LifecycleRegistry()))
    }
    // TODO("Implement SplashScreen")
    // TODO("Implement MainViewModel")
    // TODO("Get spotify user authorization code after login via deep link")
    // TODO("Initialize Napier")
    App(
        component = rootComponent,
        modifier = Modifier.fillMaxSize()
    )
}

fun onDeepLink(url: String) {
    // TODO("Check deep link working or not")
    Napier.d(message = url, tag = "IOS")
}