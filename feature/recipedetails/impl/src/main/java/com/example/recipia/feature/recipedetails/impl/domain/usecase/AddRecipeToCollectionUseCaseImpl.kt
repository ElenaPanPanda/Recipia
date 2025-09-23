package com.example.recipia.feature.recipedetails.impl.domain.usecase

import com.example.recipia.feature.recipedetails.impl.data.repo.RecipeDetailsRepository
import javax.inject.Inject

internal class AddRecipeToCollectionUseCaseImpl @Inject constructor(
    private val repository: RecipeDetailsRepository
) : AddRecipeToCollectionUseCase {
    override suspend fun add(collectionId: String, recipeId: String) {
        repository.addRecipeToCollection(collectionId, recipeId)
    }
}