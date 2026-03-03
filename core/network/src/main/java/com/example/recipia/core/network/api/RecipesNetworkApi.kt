package com.example.recipia.core.network.api

import com.example.recipia.core.network.dto.RecipeListResponse
import retrofit2.http.GET

internal interface RecipesNetworkApi {
    @GET("recipes/short")
    suspend fun getRecipes(): RecipeListResponse
}