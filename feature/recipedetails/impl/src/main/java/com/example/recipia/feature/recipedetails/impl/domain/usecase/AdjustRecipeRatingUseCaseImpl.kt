package com.example.recipia.feature.recipedetails.impl.domain.usecase

import com.example.recipia.feature.recipedetails.impl.data.repo.RecipeDetailsRepository
import javax.inject.Inject

internal class AdjustRecipeRatingUseCaseImpl @Inject constructor(
    private val recipeDetailsRepository: RecipeDetailsRepository
) : AdjustRecipeRatingUseCase {
    override suspend fun adjustRating(recipeId: String, rating: Float) {
        recipeDetailsRepository.adjustRecipeRating(recipeId, rating)
    }
}
