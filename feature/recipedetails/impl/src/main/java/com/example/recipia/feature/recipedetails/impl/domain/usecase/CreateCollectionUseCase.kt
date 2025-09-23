package com.example.recipia.feature.recipedetails.impl.domain.usecase

interface CreateCollectionUseCase {
    suspend fun create(collectionName: String, recipeId: String)
}