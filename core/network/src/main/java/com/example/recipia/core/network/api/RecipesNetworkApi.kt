package com.example.recipia.core.network.api

import com.example.recipia.core.common.model.FullRecipe
import com.example.recipia.core.network.dto.FullRecipeResponse
import com.example.recipia.core.network.dto.RecipeListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

internal interface RecipesNetworkApi {
    @GET("recipes/short")
    suspend fun getRecipes(): RecipeListResponse

    @POST("recipes")
    suspend fun addRecipe(@Body body: FullRecipe): FullRecipeResponse
}