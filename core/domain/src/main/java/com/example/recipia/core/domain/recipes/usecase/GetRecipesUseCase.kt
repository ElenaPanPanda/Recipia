package com.example.recipia.core.domain.recipes.usecase

import com.example.recipia.core.common.model.ShortRecipe
import com.example.recipia.core.domain.recipes.mappers.RecipeListMapper
import com.example.recipia.core.network.repository.RecipeRepository
import javax.inject.Inject

interface GetRecipesUseCase {
    suspend fun getRecipes(): List<ShortRecipe>
}

internal class GetRecipesUseCaseImpl @Inject constructor(
    private val mapper: RecipeListMapper,
    private val repository: RecipeRepository,
) : GetRecipesUseCase {
    override suspend fun getRecipes() = mapper.convert(repository.getRecipes())
}