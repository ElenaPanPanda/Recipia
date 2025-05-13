package recipia.feature.add_recipe.impl.ui

sealed interface AddRecipeEvent {
    data class OnTitleInputChanged(val value: String) : AddRecipeEvent
    data class OnInstructionsInputChanged(val value: String) : AddRecipeEvent
    data object OnSaveClicked : AddRecipeEvent
}