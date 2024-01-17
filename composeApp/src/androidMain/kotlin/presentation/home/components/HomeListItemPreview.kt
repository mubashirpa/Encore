package presentation.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import presentation.theme.EncoreTheme

@Preview
@Composable
private fun HomeListItemPreview() {
    EncoreTheme {
        HomeListItem(name = "Hello", imageUrl = "")
    }
}