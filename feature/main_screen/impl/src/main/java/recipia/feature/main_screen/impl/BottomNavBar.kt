package recipia.feature.main_screen.impl

import androidx.compose.foundation.Image
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import recipia.core.ui.icons.Icons
import recipia.core.ui.theme.basil
import recipia.core.ui.theme.creamWhite

@Composable
fun BottomNavBar(
    selectedIndex: Int,
    onItemSelected: (Int, MenuItem) -> Unit
) {
    val navigationItems = listOf(
        NavigationItem("Recipes", icon = Icons.collections1, MenuItem.RecipeList),
        NavigationItem("Add recipe", icon = Icons.addRecipe, MenuItem.AddRecipe)
    )

    NavigationBar(containerColor = creamWhite) {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = { onItemSelected(index, item.screen) },
                icon = {
                    Image(
                        painter = painterResource(item.icon),
                        contentDescription = item.title
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = basil,
                    indicatorColor = basil
                )
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