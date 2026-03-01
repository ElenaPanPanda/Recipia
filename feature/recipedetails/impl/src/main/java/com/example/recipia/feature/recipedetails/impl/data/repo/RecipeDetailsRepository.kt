package com.example.recipia.feature.recipedetails.impl.data.repo

import com.example.recipia.feature.recipedetails.impl.data.dto.AddRecipeToCollectionResponse
import com.example.recipia.feature.recipedetails.impl.data.dto.CreateCollectionRequest
import com.example.recipia.feature.recipedetails.impl.data.dto.CreateCollectionResponse
import com.example.recipia.feature.recipedetails.impl.data.dto.GetCollectionsResponse
import com.example.recipia.feature.recipedetails.impl.data.dto.GetRecipeResponse

interface RecipeDetailsRepository {
    suspend fun getRecipe(id: String): GetRecipeResponse

    suspend fun getCollections(): GetCollectionsResponse

    suspend fun createCollection(body: CreateCollectionRequest): CreateCollectionResponse

    suspend fun addRecipeToCollection(
        collectionId: String,
        recipeId: String
    ): AddRecipeToCollectionResponse

    suspend fun deleteRecipe(id: String)
}