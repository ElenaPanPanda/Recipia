package com.example.recipia.feature.recipedetails.impl.domain.usecase

import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredient

interface AddIngredientToShoppingListUseCase {
    suspend fun add(recipeName: String, ingredient: DetailedIngredient)
}