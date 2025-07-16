package com.example.recipia.feature.recipedetails.impl.domain.usecase

import com.example.recipia.feature.recipedetails.impl.data.repo.RecipeDetailsRepository
import com.example.recipia.feature.recipedetails.impl.domain.mapper.RecipeToDetailedMapper
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedRecipe
import javax.inject.Inject

internal class GetRecipeUseCaseImpl @Inject constructor(
    private val mapper: RecipeToDetailedMapper,
    private val repository: RecipeDetailsRepository
) : GetRecipeUseCase {
    override suspend fun getRecipe(id: String): DetailedRecipe =
        mapper.convert(repository.getRecipe(id))
}