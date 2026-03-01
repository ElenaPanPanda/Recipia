package com.example.recipia.feature.collections.impl.domain.usecase

import com.example.recipia.core.common.model.UserCollection
import com.example.recipia.feature.collections.impl.data.repo.CollectionsRepository
import javax.inject.Inject

internal class GetCollectionsUseCaseImpl @Inject constructor(
    private val repository: CollectionsRepository
) : GetCollectionsUseCase {
    override suspend fun get(): List<UserCollection> =
        repository.getCollectionsList().collections
}