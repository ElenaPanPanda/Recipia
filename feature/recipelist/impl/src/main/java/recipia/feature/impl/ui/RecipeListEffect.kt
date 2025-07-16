package recipia.feature.impl.ui

sealed class RecipeListEffect {
    data class ShowSnackBar(val message: String) : RecipeListEffect()
    data class NavigateToRecipeDetails(val recipeId: String) : RecipeListEffect()
    data object NavigateToAddRecipe : RecipeListEffect()
}