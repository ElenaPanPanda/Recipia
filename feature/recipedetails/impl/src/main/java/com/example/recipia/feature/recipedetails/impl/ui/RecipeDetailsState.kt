package com.example.recipia.feature.recipedetails.impl.ui

import com.example.recipia.feature.recipedetails.impl.domain.model.CollectionWithSelectedOption
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedRecipe

sealed interface RecipeDetailsState {
    data object Loading : RecipeDetailsState
    data class Error(val message: String) : RecipeDetailsState
    data class Success(
        val recipe: DetailedRecipe,
        val isAllIngredientsChecked: Boolean = false,
        val collections: List<CollectionWithSelectedOption>? = null,
        val newCollectionValue: String = "",
        val recipeIdToSave: String? = null,
        val saveRecipeInCollectionButtonIsEnabled: Boolean = false,
    ) : RecipeDetailsState
}