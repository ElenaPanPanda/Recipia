package recipia.feature.main_screen.impl

sealed interface MenuItem {
    data object RecipeList : MenuItem
    data object AddRecipe : MenuItem
}

data class NavigationItem(
    val title: String,
    val icon: Int,
    val screen: MenuItem,
)