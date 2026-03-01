package com.example.recipia.feature.recipedetails.impl.domain.usecase

interface DeleteRecipeUseCase {
    suspend fun delete(recipeId: String)
}
