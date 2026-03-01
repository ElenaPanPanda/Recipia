package com.example.recipia.feature.collections.impl.data.repo

import com.example.recipia.feature.collections.impl.data.api.CollectionsNetworkApi
import com.example.recipia.feature.collections.impl.data.dto.GetCollectionByIdResponse
import com.example.recipia.feature.collections.impl.data.dto.GetCollectionsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class CollectionsRepositoryImpl @Inject constructor(
    private val api: CollectionsNetworkApi
) : CollectionsRepository {
    override suspend fun getCollectionsList(): GetCollectionsResponse =
        withContext(Dispatchers.IO) { api.getCollections() }

    override suspend fun getCollectionById(id: String): GetCollectionByIdResponse =
        withContext(Dispatchers.IO) { api.getCollectionById(id) }
}