package com.example.recipia.feature.recipedetails.impl.domain.usecase

import com.example.recipia.feature.recipedetails.impl.data.repo.RecipeDetailsRepository
import com.example.recipia.feature.recipedetails.impl.domain.mapper.CollectionToCollectionWithSelectedOptionMapper
import com.example.recipia.feature.recipedetails.impl.domain.model.CollectionWithSelectedOption
import com.example.recipia.feature.recipedetails.impl.ui.RecipeDetailsViewModel
import javax.inject.Inject

internal class GetCollectionsUseCaseImpl @Inject constructor(
    private val mapper: CollectionToCollectionWithSelectedOptionMapper,
    private val repository: RecipeDetailsRepository
) : GetCollectionsUseCase {
    override suspend fun getCollections(): List<CollectionWithSelectedOption> =
        //mapper.convert(repository.getCollections())
        RecipeDetailsViewModel.COLLECTIONS
}