package recipia.feature.recipe_list_impl.ui

sealed interface RecipeListEvent {
    data class OnLikeClicked(val recipeId: String) : RecipeListEvent
}