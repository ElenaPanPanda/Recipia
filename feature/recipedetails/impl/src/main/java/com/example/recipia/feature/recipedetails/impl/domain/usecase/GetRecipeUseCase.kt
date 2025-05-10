package com.example.recipia.feature.recipedetails.impl.domain.usecase

import com.example.recipia.core.common.model.FullRecipe

interface GetRecipeUseCase {
    suspend fun getRecipe(id: String): FullRecipe
}