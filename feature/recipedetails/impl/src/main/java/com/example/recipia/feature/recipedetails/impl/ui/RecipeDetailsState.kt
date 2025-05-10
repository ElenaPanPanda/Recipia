package com.example.recipia.feature.recipedetails.impl.ui

import androidx.compose.runtime.Stable
import com.example.recipia.core.common.model.FullRecipe

@Stable
data class RecipeDetailsState(
    val isLoading: Boolean = false,
    val recipeId: String = "",
    val recipe: FullRecipe? = null,
)