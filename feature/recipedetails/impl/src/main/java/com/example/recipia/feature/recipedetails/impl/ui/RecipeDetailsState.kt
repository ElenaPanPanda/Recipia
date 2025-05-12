package com.example.recipia.feature.recipedetails.impl.ui

import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedRecipe

sealed interface RecipeDetailsState {
    data object Loading : RecipeDetailsState
    data class Error(val message: String) : RecipeDetailsState
    data class Success(val recipe: DetailedRecipe) : RecipeDetailsState
}