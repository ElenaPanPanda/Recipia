package com.example.recipia.feature.cookingmode.impl.ui

sealed interface CookingModeState {
    data object Loading : CookingModeState
    data class Error(val message: String) : CookingModeState
    data class Success(
        val recipeId: String = "",
    ) : CookingModeState
}
