package com.example.recipia.core.network.recipes

import com.example.recipia.core.common.model.FullRecipe
import com.example.recipia.core.network.dto.AdjustRecipeRatingDto
import com.example.recipia.core.network.dto.FullRecipeDto
import com.example.recipia.core.network.dto.RecipeListDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

internal interface RecipesNetworkApi {
    @GET("recipes/short")
    suspend fun getRecipes(): RecipeListDto

    @POST("recipes")
    suspend fun addRecipe(@Body body: FullRecipe): FullRecipeDto

    @PATCH("recipes/{id}/rating")
    suspend fun adjustRecipeRating(
        @Path("id") id: String,
        @Body request: AdjustRecipeRatingDto
    )

    @GET("recipes/{id}")
    suspend fun getRecipe(@Path("id") id: String): FullRecipeDto

    @DELETE("recipes/{id}")
    suspend fun deleteRecipe(@Path("id") id: String)
}