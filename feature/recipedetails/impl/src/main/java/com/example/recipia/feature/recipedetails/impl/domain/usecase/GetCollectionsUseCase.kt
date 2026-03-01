package com.example.recipia.feature.recipedetails.impl.domain.usecase

import com.example.recipia.feature.recipedetails.impl.domain.model.CollectionWithSelectedOption

interface GetCollectionsUseCase {
    suspend fun getCollections(): List<CollectionWithSelectedOption>
}