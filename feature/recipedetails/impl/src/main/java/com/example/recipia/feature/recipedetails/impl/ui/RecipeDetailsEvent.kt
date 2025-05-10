package com.example.recipia.feature.recipedetails.impl.ui

sealed interface RecipeDetailsEvent {
    data class OnEditClick(val recipeId: String) : RecipeDetailsEvent
    data class OnDeleteClick(val recipeId: String) : RecipeDetailsEvent
}