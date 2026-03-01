package com.example.recipia.feature.collections.impl.data.api

import com.example.recipia.feature.collections.impl.data.dto.GetCollectionByIdResponse
import com.example.recipia.feature.collections.impl.data.dto.GetCollectionsResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface CollectionsNetworkApi {
    @GET("/collections")
    suspend fun getCollections(): GetCollectionsResponse

    @GET("/collections/{id}")
    suspend fun getCollectionById(@Path("id") id: String): GetCollectionByIdResponse
}