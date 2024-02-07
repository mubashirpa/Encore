package presentation.theme

import androidx.compose.runtime.Composable

/**
 * This is the minimum amount of calculated contrast for a color to be used on top of the
 * surface color. These values are defined within the WCAG AA guidelines, and we use a value of
 * 3:1 which is the minimum for user-interface components.
 */
const val MIN_CONTRAST_OF_PRIMARY_VS_SURFACE = 3f

/**
 * Theme that updates the colors dynamically depending on the podcast image URL
 */
@Composable
expect fun EncoreDynamicTheme(
    imageUrl: String,
    content: @Composable () -> Unit,
)
