package com.example.recipia.feature.recipedetails.impl.data.api

import com.example.recipia.feature.recipedetails.impl.data.dto.GetRecipeResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface RecipeDetailsNetworkApi {
    @GET("recipes/{id}")
    suspend fun getRecipe(
        @Path("id") id: String,
    ): GetRecipeResponse
}