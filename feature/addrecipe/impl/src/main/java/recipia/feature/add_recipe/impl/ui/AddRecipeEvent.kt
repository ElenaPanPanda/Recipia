package recipia.feature.add_recipe.impl.ui

import recipia.feature.add_recipe.impl.domain.model.CategoryForChoose

sealed interface AddRecipeEvent {
    data class OnTitleInputChanged(val value: String) : AddRecipeEvent
    data class OnCategorySelected(val category: CategoryForChoose) : AddRecipeEvent
    data class OnInstructionsInputChanged(val value: String) : AddRecipeEvent
    data object OnSaveClicked : AddRecipeEvent
}