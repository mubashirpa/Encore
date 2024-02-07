package presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import presentation.utils.DynamicThemePrimaryColorsFromImage
import presentation.utils.contrastAgainst
import presentation.utils.rememberDominantColorState

@Composable
actual fun EncoreDynamicTheme(
    imageUrl: String,
    content: @Composable () -> Unit,
) {
    val surfaceColor = MaterialTheme.colorScheme.surface
    val dominantColorState =
        rememberDominantColorState { color ->
            // We want a color which has sufficient contrast against the surface color
            color.contrastAgainst(surfaceColor) >= MIN_CONTRAST_OF_PRIMARY_VS_SURFACE
        }
    DynamicThemePrimaryColorsFromImage(dominantColorState) {
        // Update the dominantColorState with colors coming from the podcast image URL
        LaunchedEffect(imageUrl) {
            if (imageUrl.isNotEmpty()) {
                dominantColorState.updateColorsFromImageUrl(imageUrl)
            } else {
                dominantColorState.reset()
            }
        }
        content()
    }
}
