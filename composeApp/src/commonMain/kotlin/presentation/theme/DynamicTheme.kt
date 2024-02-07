package presentation.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.kmpalette.color
import com.kmpalette.loader.rememberNetworkLoader
import com.kmpalette.rememberDominantColorState
import io.ktor.http.Url
import presentation.utils.contrastAgainst

/**
 * This is the minimum amount of calculated contrast for a color to be used on top of the
 * surface color. These values are defined within the WCAG AA guidelines, and we use a value of
 * 3:1 which is the minimum for user-interface components.
 */
const val MIN_CONTRAST_OF_PRIMARY_VS_SURFACE = 3f

/**
 * Theme that updates the colors dynamically depending on the image URL
 */
@Composable
fun EncoreDynamicTheme(
    imageUrl: String,
    content: @Composable () -> Unit,
) {
    val surfaceColor = MaterialTheme.colorScheme.surface
    val networkLoader = rememberNetworkLoader()
    val dominantColorState =
        rememberDominantColorState(
            loader = networkLoader,
            cacheSize = 12,
            isSwatchValid = { swatch ->
                swatch.color.contrastAgainst(surfaceColor) >= MIN_CONTRAST_OF_PRIMARY_VS_SURFACE
            },
        )
    LaunchedEffect(imageUrl) {
        if (imageUrl.isNotEmpty()) {
            dominantColorState.updateFrom(Url(imageUrl))
        } else {
            dominantColorState.reset()
        }
    }

    val colorScheme =
        MaterialTheme.colorScheme.copy(
            primary =
                animateColorAsState(
                    dominantColorState.color,
                    spring(stiffness = Spring.StiffnessLow),
                    label = "Primary color animation",
                ).value,
            onPrimary =
                animateColorAsState(
                    dominantColorState.onColor,
                    spring(stiffness = Spring.StiffnessLow),
                    label = "On primary color animation",
                ).value,
        )
    MaterialTheme(
        colorScheme = colorScheme,
        shapes = Shapes,
        typography = Typography,
        content = content,
    )
}
