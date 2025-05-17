package recipia.feature.add_recipe.impl.ui

sealed class AddRecipeEffect {
    data class ShowSnackBar(val message: String) : AddRecipeEffect()
    data class NavigateToRecipeDetails(val id: String) : AddRecipeEffect()
}