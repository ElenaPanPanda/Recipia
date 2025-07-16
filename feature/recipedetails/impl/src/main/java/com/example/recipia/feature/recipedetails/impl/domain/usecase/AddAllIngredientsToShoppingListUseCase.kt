package com.example.recipia.feature.recipedetails.impl.domain.usecase

import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredientSection

interface AddAllIngredientsToShoppingListUseCase {
    suspend fun add(recipeName: String, ingredients: List<DetailedIngredientSection>)
}