package com.example.recipia.feature.recipedetails.impl.domain.usecase

import com.example.recipia.core.ui.model.PlaceholderColor
import com.example.recipia.feature.recipedetails.impl.data.dto.CreateCollectionRequest
import com.example.recipia.feature.recipedetails.impl.data.repo.RecipeDetailsRepository
import javax.inject.Inject

internal class CreateCollectionUseCaseImpl @Inject constructor(
    private val repository: RecipeDetailsRepository
) : CreateCollectionUseCase {
    override suspend fun create(collectionName: String, recipeId: String) {
        val body = CreateCollectionRequest(
            collectionName = collectionName,
            collectionColor = PlaceholderColor.entries.random(),
            recipeId = recipeId,
        )
        repository.createCollection(body)
    }
}