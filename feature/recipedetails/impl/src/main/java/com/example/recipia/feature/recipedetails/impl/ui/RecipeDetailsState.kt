package com.example.recipia.feature.recipedetails.impl.ui

import com.example.recipia.core.common.model.FullRecipe
import com.example.recipia.core.common.model.UserCollectionOverview

sealed interface RecipeDetailsState {
    data object Loading : RecipeDetailsState
    data class Error(val message: String) : RecipeDetailsState
    data class Success(
        // recipe details
        val recipe: FullRecipe,
        val isAllIngredientsChecked: Boolean = false,

        // saving to collections
        val collections: List<UserCollectionOverview>? = null,
        val selectedCollectionId: String? = null,
        val saveRecipeInCollectionButtonIsEnabled: Boolean = false,
        val saveToCollectionOption: SaveToCollectionOption? = null,
    ) : RecipeDetailsState
}

sealed interface SaveToCollectionOption {
    data class Existing(val id: String) : SaveToCollectionOption
    data class New(val name: String) : SaveToCollectionOption
}