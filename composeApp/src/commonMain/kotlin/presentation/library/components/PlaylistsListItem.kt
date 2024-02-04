package presentation.library.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun PlaylistsListItem(
    name: String,
    imageUrl: String,
    artists: String?,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier =
            Modifier
                .width(120.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onClick,
                ),
    ) {
        KamelImage(
            resource = asyncPainterResource(imageUrl),
            contentDescription = null,
            modifier =
                Modifier
                    .size(120.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .indication(interactionSource, rememberRipple()),
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = name,
            color = MaterialTheme.colorScheme.onSurface,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = MaterialTheme.typography.labelLarge,
        )
        if (artists != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = artists,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.labelMedium,
            )
        }
    }
}
