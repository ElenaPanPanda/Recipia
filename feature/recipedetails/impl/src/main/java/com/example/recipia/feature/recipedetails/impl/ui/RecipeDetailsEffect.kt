package com.example.recipia.feature.recipedetails.impl.ui

sealed class RecipeDetailsEffect {
    data class ShowSnackBar(val message: String) : RecipeDetailsEffect()
}