package com.example.recipia.feature.recipedetails.impl.domain.usecase

import com.example.recipia.feature.recipedetails.impl.data.repo.RecipeDetailsRepository
import com.example.recipia.feature.recipedetails.impl.domain.mapper.RecipeMapper
import javax.inject.Inject

internal class GetRecipeUseCaseImpl @Inject constructor(
    private val mapper: RecipeMapper,
    private val repository: RecipeDetailsRepository
) : GetRecipeUseCase {
    override suspend fun getRecipe(id: String) = mapper.convert(repository.getRecipe(id))
}