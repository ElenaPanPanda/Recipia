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
}