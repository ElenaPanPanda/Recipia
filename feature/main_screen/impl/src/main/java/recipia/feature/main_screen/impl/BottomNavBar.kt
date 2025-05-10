package recipia.feature.main_screen.impl

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipia.core.ui.icons.Icons
import com.example.recipia.core.ui.theme.AppTypography
import com.example.recipia.core.ui.theme.DarkTeal
import com.example.recipia.core.ui.theme.DividerNavBarColor
import com.example.recipia.core.ui.theme.MediumTeal
import com.example.recipia.core.ui.theme.snowWhite
import com.example.recipia.core.ui.utils.NoRippleInteractionSource
import recipia.feature.add_recipe.api.AddRecipeRoutingContract
import recipia.feature.recipe_list_api.RecipeListRoutingContract

@Composable
fun BottomNavBar(
    selectedIndex: Int,
    onItemSelected: (Int, Any) -> Unit
) {
    val bottomNavItems = listOf(
        NavigationItem(
            title = "Recipes",
            icon = Icons.recipes,
            screen = RecipeListRoutingContract.RecipeList
        ),
        NavigationItem(
            title = "Collections",
            icon = Icons.bookmark,
            screen = AddRecipeRoutingContract.AddRecipe
        ),
        NavigationItem(
            title = "Calendar",
            icon = Icons.calendar,
            screen = RecipeListRoutingContract.RecipeList
        ),
        NavigationItem(
            title = "Groceries",
            icon = Icons.groceries,
            screen = AddRecipeRoutingContract.AddRecipe
        )
    )

    NavigationBar(
        modifier = Modifier
            .border(
                border = BorderStroke(1.dp, DividerNavBarColor),
                shape = RectangleShape
            ),
        containerColor = snowWhite.copy(alpha = 0.98f),
        contentColor = MediumTeal,
    ) {
        bottomNavItems.forEachIndexed { index, item ->
            val isSelected = selectedIndex == index
            NavigationBarItem(
                selected = isSelected,
                onClick = { onItemSelected(index, item.screen) },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = item.icon),
                        contentDescription = item.title,
                        modifier = Modifier.size(24.dp),
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        style = if (isSelected) AppTypography().poppinsSemiBold.copy(
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                        )
                        else AppTypography().poppinsMedium.copy(
                            fontSize = 12.sp,
                            lineHeight = 14.sp,
                        )
                    )
                },
                interactionSource = remember { NoRippleInteractionSource },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = DarkTeal,
                    unselectedIconColor = MediumTeal,
                    selectedTextColor = DarkTeal,
                    unselectedTextColor = MediumTeal,
                    indicatorColor = Color.Transparent
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