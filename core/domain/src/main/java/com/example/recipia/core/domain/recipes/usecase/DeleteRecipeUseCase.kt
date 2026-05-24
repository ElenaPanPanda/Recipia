package com.example.recipia.core.domain.recipes.usecase

import com.example.recipia.core.network.recipes.RecipeRepository
import javax.inject.Inject

interface DeleteRecipeUseCase {
    suspend operator fun invoke(recipeId: String)
}

internal class DeleteRecipeUseCaseImpl @Inject constructor(
    private val repository: RecipeRepository
) : DeleteRecipeUseCase {
    override suspend fun invoke(recipeId: String) {
        repository.deleteRecipe(recipeId)
    }
}