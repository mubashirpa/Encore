package presentation.preview

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import presentation.search_result.SearchResultScreen
import presentation.theme.EncoreTheme

@Preview(
    device = "id:pixel_7_pro",
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE,
)
@Composable
private fun SearchResultScreenPreview() {
    EncoreTheme {
        SearchResultScreen()
    }
}
