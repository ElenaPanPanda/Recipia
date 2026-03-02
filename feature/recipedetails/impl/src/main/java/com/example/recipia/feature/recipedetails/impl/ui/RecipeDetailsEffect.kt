package com.example.recipia.feature.recipedetails.impl.ui

sealed class RecipeDetailsEffect {
    data class ShowSnackBar(val message: String) : RecipeDetailsEffect()
    data object OpenCollectionsBottomSheet : RecipeDetailsEffect()
    data object NavigateBack : RecipeDetailsEffect()
    data class NavigateToCookingMode(val recipeId: String) : RecipeDetailsEffect()
}