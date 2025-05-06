package recipia.feature.main_screen.impl

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import recipia.core.ui.icons.Icons
import recipia.core.ui.theme.basil
import recipia.core.ui.theme.freshGreen
import recipia.core.ui.theme.snowWhite
import recipia.feature.add_recipe.api.AddRecipeRoutingContract
import recipia.feature.recipe_list_api.RecipeListRoutingContract

@Composable
fun BottomNavBar(
    selectedIndex: Int,
    onItemSelected: (Int, Any) -> Unit
) {
    val navigationItems = listOf(
        NavigationItem(
            title = stringResource(id = R.string.main_screen_recipes),
            icon = Icons.recipes,
            screen = RecipeListRoutingContract.RecipeList
        ),
        NavigationItem(
            title = stringResource(id = R.string.main_screen_collections),
            icon = Icons.collections1,
            screen = RecipeListRoutingContract.RecipeList
        ),
        NavigationItem(
            title = stringResource(id = R.string.main_screen_add),
            icon = Icons.addRecipe,
            screen = AddRecipeRoutingContract.AddRecipe
        ),
        NavigationItem(
            title = stringResource(id = R.string.main_screen_calendar),
            icon = Icons.collections1,
            screen = RecipeListRoutingContract.RecipeList
        ),
        NavigationItem(
            title = stringResource(id = R.string.main_screen_groceries),
            icon = Icons.groceryList,
            screen = AddRecipeRoutingContract.AddRecipe
        )
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
                    indicatorColor = freshGreen,
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