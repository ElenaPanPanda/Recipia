package com.example.recipia.feature.recipedetails.impl.domain.usecase

import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredientSection

interface UpdateShoppingListUseCase {
    suspend fun update(index: Int, updatedItem: DetailedIngredientSection)
}