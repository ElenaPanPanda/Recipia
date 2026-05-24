package com.example.recipia.core.domain.collections.usecase

import com.example.recipia.core.network.collections.CollectionsRepository
import javax.inject.Inject

interface AddRecipeToCollectionUseCase {
    suspend operator fun invoke(collectionId: String, recipeId: String)
}

internal class AddRecipeToCollectionUseCaseImpl @Inject constructor(
    private val repository: CollectionsRepository
) : AddRecipeToCollectionUseCase {
    override suspend operator fun invoke(collectionId: String, recipeId: String) {
        repository.addRecipeToCollection(collectionId, recipeId)
    }
}