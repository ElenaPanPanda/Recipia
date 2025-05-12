package com.example.recipia.feature.recipedetails.impl.domain.usecase

import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedRecipe

interface GetRecipeUseCase {
    suspend fun getRecipe(id: String): DetailedRecipe
}