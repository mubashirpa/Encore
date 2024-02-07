package presentation.theme

import androidx.compose.runtime.Composable

// TODO("Implement dynamic theme")
@Composable
actual fun EncoreDynamicTheme(
    imageUrl: String,
    content: @Composable () -> Unit,
) {
    content()
}
