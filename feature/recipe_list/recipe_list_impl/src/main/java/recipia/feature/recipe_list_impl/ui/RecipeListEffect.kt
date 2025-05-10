package recipia.feature.recipe_list_impl.ui

sealed class RecipeListEffect {
    data class ShowSnackBar(val message: String) : RecipeListEffect()
    data class NavigateToRecipeDetails(val recipeId: String) : RecipeListEffect()
}