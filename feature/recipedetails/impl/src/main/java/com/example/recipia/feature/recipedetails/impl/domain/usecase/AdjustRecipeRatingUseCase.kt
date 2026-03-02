package com.example.recipia.feature.recipedetails.impl.domain.usecase

interface AdjustRecipeRatingUseCase {
    suspend fun adjustRating(recipeId: String, rating: Float)
}
