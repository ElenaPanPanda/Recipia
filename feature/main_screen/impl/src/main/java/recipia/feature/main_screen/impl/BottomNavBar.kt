package recipia.feature.main_screen.impl

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.recipia.core.ui.icons.Icons
import com.example.recipia.core.ui.theme.LighterBeige
import com.example.recipia.core.ui.theme.AppTypography
import com.example.recipia.core.ui.theme.DarkTeal
import com.example.recipia.core.ui.theme.MediumTeal
import com.example.recipia.core.ui.theme.snowWhite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import recipia.feature.add_recipe.api.AddRecipeRoutingContract
import recipia.feature.recipe_list_api.RecipeListRoutingContract

val bottomNavItems = listOf(
    NavigationItem(
        title = "Recipes",
        icon = Icons.listAltNav,
        screen = RecipeListRoutingContract.RecipeList
    ),
    NavigationItem(
        title = "Collections",
        icon = Icons.bookmarkNav,
        screen = AddRecipeRoutingContract.AddRecipe
    ),
    NavigationItem(
        title = "Calendar",
        icon = Icons.calendarAltNav,
        screen = RecipeListRoutingContract.RecipeList
    ),
    NavigationItem(
        title = "Groceries",
        icon = Icons.shoppingBasketNav,
        screen = AddRecipeRoutingContract.AddRecipe
    )
)

@Composable
fun BottomNavBar(
    selectedIndex: Int,
    onItemSelected: (Int, Any) -> Unit
) {
    NavigationBar(
        containerColor = snowWhite,
        contentColor = MediumTeal,
        tonalElevation = 14.dp
    ) {
        bottomNavItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = { onItemSelected(index, item.screen) },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = item.icon),
                        contentDescription = item.title,
                        modifier = Modifier.size(20.dp),
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        style = AppTypography().labell
                    )
                },
                interactionSource = remember { NoRippleInteractionSource },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = DarkTeal,
                    unselectedIconColor = MediumTeal,
                    selectedTextColor = DarkTeal,
                    unselectedTextColor = MediumTeal,
                    indicatorColor = LighterBeige
                )
            )
        }
    }
}

object NoRippleInteractionSource : MutableInteractionSource {
    override val interactions: Flow<Interaction> = emptyFlow()
    override suspend fun emit(interaction: Interaction) {}
    override fun tryEmit(interaction: Interaction): Boolean = true
}