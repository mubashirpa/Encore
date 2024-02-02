package presentation.home_container.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import presentation.utils.verticalGradientScrim

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(
    title: String,
    profileImage: String,
    actions: @Composable (RowScope.() -> Unit) = {},
    scrollBehavior: TopAppBarScrollBehavior,
) {
    val appBarColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.87f)

    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .verticalGradientScrim(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.38f),
                    startYPercentage = 1f,
                    endYPercentage = 0f,
                ),
    ) {
        TopAppBar(
            title = {
                Text(
                    text = title,
                    modifier = Modifier.padding(start = 8.dp),
                )
            },
            navigationIcon = {
                KamelImage(
                    resource = asyncPainterResource(profileImage),
                    contentDescription = null,
                    modifier =
                        Modifier
                            .padding(start = 16.dp)
                            .size(30.dp)
                            .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    onFailure = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                        )
                    },
                )
            },
            actions = {
                actions()
                Spacer(Modifier.width(16.dp))
            },
            colors =
                TopAppBarDefaults.topAppBarColors(
                    containerColor = appBarColor,
                    scrolledContainerColor = appBarColor,
                ),
            scrollBehavior = scrollBehavior,
        )
    }
}
