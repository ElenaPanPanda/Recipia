package com.example.recipia.feature.recipedetails.impl.domain.usecase

import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredient
import kotlinx.coroutines.flow.Flow

interface CheckAddedIngredientsInShoppingListUseCase {
    suspend fun getAddedIngredients(recipeName: String): Flow<List<DetailedIngredient>>
}