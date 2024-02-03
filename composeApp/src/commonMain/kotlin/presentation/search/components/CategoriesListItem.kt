package presentation.search.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoriesListItem(
    name: String,
    imageUrl: String,
    onClick: () -> Unit,
) {
    val colorStops =
        arrayOf(
            0.3f to Color.Transparent,
            1f to MaterialTheme.colorScheme.primary.copy(alpha = 0.40f),
        )

    Box(
        modifier =
            Modifier
                .aspectRatio(1F)
                .clip(MaterialTheme.shapes.medium)
                .clickable(onClick = onClick),
    ) {
        KamelImage(
            resource = asyncPainterResource(imageUrl),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
        )
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(Brush.verticalGradient(colorStops = colorStops)),
        )
        Text(
            text = name,
            modifier =
                Modifier
                    .padding(8.dp)
                    .align(Alignment.BottomEnd)
                    .basicMarquee(iterations = 2),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}
