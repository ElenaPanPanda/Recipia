package recipia.feature.main_screen.impl

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import recipia.core.ui.icons.Icons
import recipia.core.ui.theme.basil
import recipia.core.ui.theme.freshGreen
import recipia.core.ui.theme.snowWhite

@Composable
fun BottomNavBar(
    selectedIndex: Int,
    onItemSelected: (Int, MenuItem) -> Unit
) {
    val navigationItems = listOf(
        NavigationItem("Recipes", icon = Icons.collections1, MenuItem.RecipeList),
        NavigationItem("Add recipe", icon = Icons.addRecipe, MenuItem.AddRecipe),
        NavigationItem("Recipes", icon = Icons.collections1, MenuItem.RecipeList),
        NavigationItem("Add recipe", icon = Icons.addRecipe, MenuItem.AddRecipe)
    )

    NavigationBar(containerColor = snowWhite) {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = { onItemSelected(index, item.screen) },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = item.icon),
                        contentDescription = item.title,
                        modifier = Modifier.size(28.dp),
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = snowWhite,
                    unselectedIconColor = basil,
                    indicatorColor = freshGreen
                ),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomNavBarPreview() {
    BottomNavBar(
        selectedIndex = 0,
        onItemSelected = { _, _ -> }
    )
}