package com.example.recipia.core.domain.collections.usecase

import com.example.recipia.core.network.collections.CollectionsRepository
import com.example.recipia.core.network.dto.CreateCollectionRequestDto
import com.example.recipia.core.ui.model.PlaceholderColor
import javax.inject.Inject

interface CreateCollectionUseCase {
    suspend operator fun invoke(collectionName: String, recipeId: String)
}

internal class CreateCollectionUseCaseImpl @Inject constructor(
    private val repository: CollectionsRepository
) : CreateCollectionUseCase {
    override suspend fun invoke(collectionName: String, recipeId: String) {
        val collectionRequest = CreateCollectionRequestDto(
            collectionName = collectionName,
            collectionColor = PlaceholderColor.entries.random(),
            recipeId = recipeId,
        )
        repository.createCollection(collectionRequest)
    }
}