package com.example.recipia.feature.recipedetails.impl.data.repo

import com.example.recipia.feature.recipedetails.impl.data.api.RecipeDetailsNetworkApi
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