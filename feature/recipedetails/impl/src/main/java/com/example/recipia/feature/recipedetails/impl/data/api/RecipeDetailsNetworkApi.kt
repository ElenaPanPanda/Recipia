package com.example.recipia.feature.recipedetails.impl.data.api

import com.example.recipia.feature.recipedetails.impl.data.dto.AddRecipeToCollectionResponse
import com.example.recipia.feature.recipedetails.impl.data.dto.CreateCollectionRequest
import com.example.recipia.feature.recipedetails.impl.data.dto.CreateCollectionResponse
import com.example.recipia.feature.recipedetails.impl.data.dto.GetCollectionsResponse
import com.example.recipia.feature.recipedetails.impl.data.dto.GetRecipeResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

internal interface RecipeDetailsNetworkApi {
    @GET("recipes/{id}")
    suspend fun getRecipe(@Path("id") id: String): GetRecipeResponse

    @GET("/collections")
    suspend fun getCollections(): GetCollectionsResponse

    @POST("/collections/create")
    suspend fun createCollection(@Body body: CreateCollectionRequest): CreateCollectionResponse

    @PATCH("/collections/{collectionId}/{recipeId}")
    suspend fun addRecipeToCollection(
        @Path("collectionId") collectionId: String,
        @Path("recipeId") recipeId: String
    ): AddRecipeToCollectionResponse
}