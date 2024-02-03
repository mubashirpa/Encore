package presentation.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun AlbumsListItem(
    name: String,
    imageUrl: String,
    artists: String?,
    typeAndYear: String?,
    onClick: () -> Unit,
) {
    PlalistsListItemContent(
        title = name,
        imageUrl = imageUrl,
        subTitle = artists,
        moreInfo = typeAndYear,
        onClick = onClick,
    )
}

@Composable
fun ArtistsListItem(
    name: String,
    imageUrl: String,
    onClick: () -> Unit,
) {
    ListItem(
        headlineContent = {
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        },
        modifier = Modifier.clickable(onClick = onClick),
        leadingContent = {
            KamelImage(
                resource = asyncPainterResource(imageUrl),
                contentDescription = null,
                modifier =
                    Modifier
                        .size(48.dp)
                        .clip(CircleShape),
                contentScale = ContentScale.Crop,
            )
        },
    )
}

@Composable
fun PlalistsListItem(
    name: String,
    imageUrl: String,
    owner: String?,
    onClick: () -> Unit,
) {
    PlalistsListItemContent(
        title = name,
        imageUrl = imageUrl,
        subTitle = owner,
        onClick = onClick,
    )
}

@Composable
fun TracksListItem(
    name: String,
    imageUrl: String,
    artists: String,
    onClick: () -> Unit,
) {
    ListItem(
        headlineContent = {
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        },
        modifier = Modifier.clickable(onClick = onClick),
        supportingContent = {
            Text(
                text = artists,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        },
        leadingContent = {
            KamelImage(
                resource = asyncPainterResource(imageUrl),
                contentDescription = null,
                modifier =
                    Modifier
                        .size(56.dp)
                        .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop,
            )
        },
        trailingContent = {
            IconButton(
                onClick = { /*TODO*/ },
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null,
                )
            }
        },
    )
}

@Composable
fun ShowsListItem(
    name: String,
    imageUrl: String,
    publisher: String?,
    onClick: () -> Unit,
) {
    PlalistsListItemContent(
        title = name,
        imageUrl = imageUrl,
        subTitle = publisher,
        onClick = onClick,
    )
}

@Composable
private fun PlalistsListItemContent(
    title: String,
    imageUrl: String,
    subTitle: String? = null,
    moreInfo: String? = null,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier =
            Modifier.clickable(
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
                    .aspectRatio(1F)
                    .clip(MaterialTheme.shapes.medium)
                    .indication(interactionSource, rememberRipple()),
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            style = MaterialTheme.typography.bodyLarge,
        )
        if (subTitle != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subTitle,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        if (moreInfo != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = moreInfo,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}
