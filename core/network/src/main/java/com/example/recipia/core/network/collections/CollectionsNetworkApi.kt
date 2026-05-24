package com.example.recipia.core.network.collections

import com.example.recipia.core.network.dto.AddRecipeToCollectionDto
import com.example.recipia.core.network.dto.CreateCollectionRequestDto
import com.example.recipia.core.network.dto.CreateCollectionResponseDto
import com.example.recipia.core.network.dto.GetCollectionsDto
import com.example.recipia.core.network.dto.UserCollectionDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

internal interface CollectionsNetworkApi {
    @GET("/collections")
    suspend fun getCollections(): GetCollectionsDto

    @GET("/collections/{id}")
    suspend fun getCollectionById(@Path("id") id: String): UserCollectionDto

    @POST("/collections/create")
    suspend fun createCollection(@Body body: CreateCollectionRequestDto): CreateCollectionResponseDto

    @PATCH("/collections/{collectionId}/{recipeId}")
    suspend fun addRecipeToCollection(
        @Path("collectionId") collectionId: String,
        @Path("recipeId") recipeId: String
    ): AddRecipeToCollectionDto
}