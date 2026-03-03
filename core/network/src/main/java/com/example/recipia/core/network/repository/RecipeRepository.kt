package com.example.recipia.core.network.repository

import com.example.recipia.core.common.model.FullRecipe
import com.example.recipia.core.network.api.RecipesNetworkApi
import com.example.recipia.core.network.dto.FullRecipeResponse
import com.example.recipia.core.network.dto.RecipeListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface RecipeRepository {
    suspend fun getRecipes(): RecipeListResponse

    suspend fun addRecipe(recipe: FullRecipe): FullRecipeResponse
}


internal class RecipeRepositoryImpl(private val api: RecipesNetworkApi) : RecipeRepository {
    override suspend fun getRecipes() =
        withContext(Dispatchers.IO) { api.getRecipes() }

    override suspend fun addRecipe(recipe: FullRecipe): FullRecipeResponse =
        withContext(Dispatchers.IO) { api.addRecipe(recipe) }
}