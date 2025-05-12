package com.example.recipia.feature.recipedetails.impl.ui

sealed interface RecipeDetailsEvent {
    data class OnEditClicked(val recipeId: String) : RecipeDetailsEvent
    data class OnSaveClicked(val recipeId: String) : RecipeDetailsEvent
    data class OnCalendarClicked(val recipeId: String) : RecipeDetailsEvent
    data class OnShareClicked(val recipeId: String) : RecipeDetailsEvent
    data class OnDeleteClicked(val recipeId: String) : RecipeDetailsEvent
}