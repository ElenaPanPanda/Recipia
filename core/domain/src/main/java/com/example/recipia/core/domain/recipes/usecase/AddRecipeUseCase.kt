package com.example.recipia.core.domain.recipes.usecase

import com.example.recipia.core.common.model.FullRecipe
import com.example.recipia.core.network.recipes.RecipeRepository
import javax.inject.Inject

interface AddRecipeUseCase {
    suspend operator fun invoke(recipe: FullRecipe): String
}

internal class AddRecipeUseCaseImpl @Inject constructor(
    private val repository: RecipeRepository
) : AddRecipeUseCase {
    override suspend operator fun invoke(recipe: FullRecipe) =
        repository.addRecipe(recipe).recipe.id
}