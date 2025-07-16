package com.example.recipia.feature.recipedetails.impl.ui

import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredient
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredientSection

sealed interface RecipeDetailsEvent {
    data class OnEditClicked(val recipeId: String) : RecipeDetailsEvent
    data class OnSaveClicked(val recipeId: String) : RecipeDetailsEvent
    data class OnCalendarClicked(val recipeId: String) : RecipeDetailsEvent
    data class OnShareClicked(val recipeId: String) : RecipeDetailsEvent
    data class OnDeleteClicked(val recipeId: String) : RecipeDetailsEvent
    data class OnAddAllIngredientsClicked(
        val recipeName: String,
        val ingredients: List<DetailedIngredientSection>
    ) : RecipeDetailsEvent
    data class OnAddIngredientClicked(
        val recipeName: String,
        val ingredient: DetailedIngredient
    ) : RecipeDetailsEvent
}