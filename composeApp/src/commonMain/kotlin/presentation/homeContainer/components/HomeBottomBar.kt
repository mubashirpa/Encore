package presentation.homeContainer.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import navigation.HomeContainerComponent

@Composable
fun HomeBottomBar(component: HomeContainerComponent) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        tonalElevation = 0.dp,
    ) {
        NavigationScreen(component = component) { selected, icon, label, onClick ->
            NavigationBarItem(
                selected = selected,
                onClick = onClick,
                icon = {
                    Icon(
                        painter = icon,
                        contentDescription = label,
                    )
                },
                label = {
                    Text(text = label)
                },
                colors =
                    NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        indicatorColor = MaterialTheme.colorScheme.background,
                        unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                        unselectedTextColor = MaterialTheme.colorScheme.onBackground,
                    ),
            )
        }
    }
}
