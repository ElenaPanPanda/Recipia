package com.example.recipia.core.domain.recipes.usecase

import com.example.recipia.core.network.recipes.RecipeRepository
import javax.inject.Inject

interface AdjustRecipeRatingUseCase {
    suspend operator fun invoke(recipeId: String, rating: Float)
}

internal class AdjustRecipeRatingUseCaseImpl @Inject constructor(
    private val repository: RecipeRepository
): AdjustRecipeRatingUseCase {
    override suspend operator fun invoke(recipeId: String, rating: Float){
        repository.adjustRecipeRating(recipeId, rating)
    }
}