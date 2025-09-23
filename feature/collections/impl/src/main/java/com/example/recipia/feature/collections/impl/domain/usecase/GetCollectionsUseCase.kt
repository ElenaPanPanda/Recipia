package com.example.recipia.feature.collections.impl.domain.usecase

import com.example.recipia.core.common.model.UserCollection

interface GetCollectionsUseCase {
    suspend fun get(): List<UserCollection>
}