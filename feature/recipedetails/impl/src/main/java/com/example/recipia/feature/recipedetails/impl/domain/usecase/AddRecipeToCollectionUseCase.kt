package com.example.recipia.feature.recipedetails.impl.domain.usecase

interface AddRecipeToCollectionUseCase {
    suspend fun add(collectionId: String, recipeId: String)
}