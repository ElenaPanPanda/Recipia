package com.example.recipia.core.network.recipes

import com.example.recipia.core.common.model.FullRecipe
import com.example.recipia.core.network.dto.AdjustRecipeRatingDto
import com.example.recipia.core.network.dto.FullRecipeDto
import com.example.recipia.core.network.dto.RecipeListDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface RecipeRepository {
    suspend fun getRecipes(): RecipeListDto

    suspend fun getRecipe(id: String): FullRecipeDto

    suspend fun addRecipe(recipe: FullRecipe): FullRecipeDto

    suspend fun deleteRecipe(id: String)

    suspend fun adjustRecipeRating(id: String, rating: Float)
}


internal class RecipeRepositoryImpl(private val api: RecipesNetworkApi) : RecipeRepository {
    override suspend fun getRecipes() =
        withContext(Dispatchers.IO) { api.getRecipes() }

    override suspend fun getRecipe(id: String): FullRecipeDto =
        withContext(Dispatchers.IO) { api.getRecipe(id) }

    override suspend fun addRecipe(recipe: FullRecipe): FullRecipeDto =
        withContext(Dispatchers.IO) { api.addRecipe(recipe) }

    override suspend fun deleteRecipe(id: String) =
        withContext(Dispatchers.IO) {
            api.deleteRecipe(id)
        }

    override suspend fun adjustRecipeRating(id: String, rating: Float) =
        withContext(Dispatchers.IO) {
            api.adjustRecipeRating(id, AdjustRecipeRatingDto(rating))
        }
}