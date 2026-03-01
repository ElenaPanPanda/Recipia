package com.example.recipia.feature.recipedetails.impl.data.repo

import com.example.recipia.feature.recipedetails.impl.data.api.RecipeDetailsNetworkApi
import com.example.recipia.feature.recipedetails.impl.data.dto.AddRecipeToCollectionResponse
import com.example.recipia.feature.recipedetails.impl.data.dto.CreateCollectionRequest
import com.example.recipia.feature.recipedetails.impl.data.dto.CreateCollectionResponse
import com.example.recipia.feature.recipedetails.impl.data.dto.GetCollectionsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class RecipeDetailsRepositoryImpl @Inject constructor(
    private val api: RecipeDetailsNetworkApi
) : RecipeDetailsRepository {
    override suspend fun getRecipe(id: String) =
        withContext(Dispatchers.IO) {
            api.getRecipe(id)
        }

    override suspend fun getCollections(): GetCollectionsResponse =
        withContext(Dispatchers.IO) {
            api.getCollections()
        }

    override suspend fun createCollection(body: CreateCollectionRequest): CreateCollectionResponse =
        withContext(Dispatchers.IO) {
            api.createCollection(body)
        }

    override suspend fun addRecipeToCollection(
        collectionId: String,
        recipeId: String
    ): AddRecipeToCollectionResponse =
        withContext(Dispatchers.IO) {
            api.addRecipeToCollection(collectionId, recipeId)
        }

    override suspend fun deleteRecipe(id: String) =
        withContext(Dispatchers.IO) {
            api.deleteRecipe(id)
        }
}