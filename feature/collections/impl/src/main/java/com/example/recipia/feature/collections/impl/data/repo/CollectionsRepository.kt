package com.example.recipia.feature.collections.impl.data.repo

import com.example.recipia.feature.collections.impl.data.dto.GetCollectionByIdResponse
import com.example.recipia.feature.collections.impl.data.dto.GetCollectionsResponse

interface CollectionsRepository {
    suspend fun getCollectionsList(): GetCollectionsResponse
    suspend fun getCollectionById(id: String): GetCollectionByIdResponse
}