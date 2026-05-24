package com.example.recipia.core.domain.recipes.usecase

import com.example.recipia.core.common.model.FullRecipe
import com.example.recipia.core.network.recipes.RecipeRepository
import javax.inject.Inject

interface GetRecipeUseCase {
    suspend operator fun invoke(id: String): FullRecipe
}

internal class GetRecipeUseCaseImpl @Inject constructor(
    private val repository: RecipeRepository
) : GetRecipeUseCase {
    override suspend fun invoke(id: String): FullRecipe =
        repository.getRecipe(id).recipe
}