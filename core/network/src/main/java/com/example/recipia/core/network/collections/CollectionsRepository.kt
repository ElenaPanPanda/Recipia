package com.example.recipia.core.network.collections

import com.example.recipia.core.network.dto.AddRecipeToCollectionDto
import com.example.recipia.core.network.dto.CreateCollectionRequestDto
import com.example.recipia.core.network.dto.CreateCollectionResponseDto
import com.example.recipia.core.network.dto.GetCollectionsDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface CollectionsRepository {
    suspend fun getCollections(): GetCollectionsDto

    suspend fun createCollection(body: CreateCollectionRequestDto): CreateCollectionResponseDto

    suspend fun addRecipeToCollection(
        collectionId: String,
        recipeId: String
    ): AddRecipeToCollectionDto
}


internal class CollectionsRepositoryImpl(private val api: CollectionsNetworkApi) :
    CollectionsRepository {
    override suspend fun getCollections(): GetCollectionsDto =
        withContext(Dispatchers.IO) {
            api.getCollections()
        }

    override suspend fun createCollection(body: CreateCollectionRequestDto): CreateCollectionResponseDto =
        withContext(Dispatchers.IO) {
            api.createCollection(body)
        }

    override suspend fun addRecipeToCollection(
        collectionId: String,
        recipeId: String
    ): AddRecipeToCollectionDto =
        withContext(Dispatchers.IO) {
            api.addRecipeToCollection(collectionId, recipeId)
        }
}