package com.example.recipia.feature.recipedetails.impl.domain.usecase

import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredient

interface AddIngredientToShoppingList {
    suspend fun add(recipeName: String, ingredient: DetailedIngredient)
}