package com.example.recipia.feature.recipedetails.impl.ui

import com.example.recipia.feature.recipedetails.impl.domain.model.CollectionInRecipeDetails
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedRecipe

sealed interface RecipeDetailsState {
    data object Loading : RecipeDetailsState
    data class Error(val message: String) : RecipeDetailsState
    data class Success(
        // recipe details
        val recipe: DetailedRecipe,
        val isAllIngredientsChecked: Boolean = false,

        // saving to collections
        val collections: List<CollectionInRecipeDetails>? = null,
        val selectedCollectionId: String? = null,
        val saveRecipeInCollectionButtonIsEnabled: Boolean = false,
        val saveToCollectionOption: SaveToCollectionOption? = null,
    ) : RecipeDetailsState
}

sealed interface SaveToCollectionOption {
    data class Existing(val id: String) : SaveToCollectionOption
    data class New(val name: String) : SaveToCollectionOption
}