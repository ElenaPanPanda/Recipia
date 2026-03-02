package com.example.recipia.feature.recipedetails.impl.domain.usecase

import com.example.recipia.feature.recipedetails.impl.data.repo.RecipeDetailsRepository
import javax.inject.Inject

internal class DeleteRecipeUseCaseImpl @Inject constructor(
    private val repository: RecipeDetailsRepository
) : DeleteRecipeUseCase {
    override suspend fun delete(recipeId: String) {
        repository.deleteRecipe(recipeId)
    }
}
