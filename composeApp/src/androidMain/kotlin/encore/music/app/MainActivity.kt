package encore.music.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.arkivanov.decompose.defaultComponentContext
import navigation.DefaultRootComponent
import navigation.RootComponent
import org.koin.androidx.viewmodel.ext.android.viewModel
import presentation.App
import presentation.MainUIEvent
import presentation.MainViewModel

class MainActivity : ComponentActivity() {

    private lateinit var rootComponent: RootComponent
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !viewModel.isReady.value
            }
        }
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        // TODO("Handle user authorization error")
        val authorizationCode = intent.data?.extractAuthorizationCode()

        rootComponent = DefaultRootComponent(componentContext = defaultComponentContext())

        if (authorizationCode != null) {
            intent =
                Intent(intent).setData(null) // The deep link has been handled, clear the Intent data
            viewModel.onEvent(MainUIEvent.OnAuthorizationCodeReceived(authorizationCode))
        } else {
            viewModel.onEvent(MainUIEvent.GetAccessToken)
        }

        setContent {
            App(
                component = rootComponent,
                modifier = Modifier.fillMaxSize()
            )
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val authorizationCode = intent?.data?.extractAuthorizationCode() ?: return
        viewModel.onEvent(MainUIEvent.OnAuthorizationCodeReceived(authorizationCode))
    }

    private fun Uri.extractAuthorizationCode(): String? {
        return this.getQueryParameter("code")
    }
}