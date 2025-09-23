package com.example.recipia.feature.collections.impl.domain.usecase

import com.example.recipia.core.common.model.UserCollection
import com.example.recipia.feature.collections.impl.data.repo.CollectionsRepository
import javax.inject.Inject

internal class GetCollectionByIdUseCaseImpl @Inject constructor(
    private val repository: CollectionsRepository
) : GetCollectionByIdUseCase {
    override suspend fun get(id: String): UserCollection =
        repository.getCollectionById(id).collection
}