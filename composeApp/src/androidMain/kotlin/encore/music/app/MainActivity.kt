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
import presentation.App

class MainActivity : ComponentActivity() {

    private lateinit var rootComponent: RootComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val authorizationCode = intent.data?.extractAuthorizationCode()

        rootComponent = DefaultRootComponent(
            componentContext = defaultComponentContext(),
            authorizationCode = authorizationCode
        )

        if (authorizationCode != null) {
            intent =
                Intent(intent).setData(null) // The deep link has been handled, clear the Intent data
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
        rootComponent.onDeepLink(authorizationCode)
    }

    private fun Uri.extractAuthorizationCode(): String? {
        return this.getQueryParameter("code")
    }
}