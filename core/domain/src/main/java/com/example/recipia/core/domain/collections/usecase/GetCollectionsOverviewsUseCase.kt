package com.example.recipia.core.domain.collections.usecase

import com.example.recipia.core.common.model.UserCollectionOverview
import com.example.recipia.core.domain.collections.mappers.CollectionOverviewMapper
import com.example.recipia.core.network.collections.CollectionsRepository
import javax.inject.Inject

interface GetCollectionsOverviewsUseCase {
    suspend operator fun invoke(): List<UserCollectionOverview>
}

internal class GetCollectionsOverviewsUseCaseImpl @Inject constructor(
    private val repository: CollectionsRepository,
    private val mapper: CollectionOverviewMapper,
) : GetCollectionsOverviewsUseCase {
    override suspend fun invoke(): List<UserCollectionOverview> {
        return mapper.convert(repository.getCollections())
    }
}