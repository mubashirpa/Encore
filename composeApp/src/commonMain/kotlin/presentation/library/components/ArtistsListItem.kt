package presentation.library.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun ArtistsListItem(
    imageUrl: String,
    onClick: () -> Unit,
) {
    KamelImage(
        resource = asyncPainterResource(imageUrl),
        contentDescription = null,
        modifier =
            Modifier
                .size(48.dp)
                .clip(CircleShape)
                .clickable(onClick = onClick),
        contentScale = ContentScale.Crop,
    )
}
