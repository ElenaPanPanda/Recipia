package recipia.feature.recipe_list_impl.ui

sealed class RecipeListEffect {
    data class ShowSnackBar(val message: String) : RecipeListEffect()
}