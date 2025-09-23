package com.example.recipia.feature.collections.impl.domain.usecase

import com.example.recipia.core.common.model.UserCollection

interface GetCollectionByIdUseCase {
    suspend fun get(id: String): UserCollection
}