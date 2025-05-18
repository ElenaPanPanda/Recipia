package recipia.feature.add_recipe.impl.ui

import recipia.feature.add_recipe.impl.domain.model.CategoryForChoose

sealed interface AddRecipeEvent {
    data class OnTitleInputChanged(val value: String) : AddRecipeEvent
    data class OnCategorySelected(val category: CategoryForChoose) : AddRecipeEvent
    data class OnIngredientTitleValueChange(val value: String, val index: Int) : AddRecipeEvent
    data class OnIngredientNameValueChange(
        val value: String,
        val ingredientsGroupIndex: Int,
        val ingredientIndex: Int
    ) : AddRecipeEvent

    data class OnIngredientAmountValueChange(
        val value: String,
        val ingredientsGroupIndex: Int,
        val ingredientIndex: Int
    ) : AddRecipeEvent

    data class OnIngredientRemoveClicked(val ingredientsGroupIndex: Int, val ingredientIndex: Int) :
        AddRecipeEvent

    data class OnAddIngredientClicked(val ingredientsGroupIndex: Int) : AddRecipeEvent
    data class OnRemoveIngredientsCardClicked(val ingredientsGroupIndex: Int) : AddRecipeEvent
    data object OnAddIngredientsGroupClicked : AddRecipeEvent
    data class OnInstructionsInputChanged(val value: String) : AddRecipeEvent
    data object OnSaveClicked : AddRecipeEvent
    data object OpenExitDialog : AddRecipeEvent
}