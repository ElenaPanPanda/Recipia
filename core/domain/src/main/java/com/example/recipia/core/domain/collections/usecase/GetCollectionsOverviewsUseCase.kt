package com.example.recipia.core.domain.collections.usecase

import com.example.recipia.core.common.model.UserCollectionOverview
import com.example.recipia.core.domain.collections.mappers.CollectionsOverviewMapper
import com.example.recipia.core.network.collections.CollectionsRepository
import javax.inject.Inject

interface GetCollectionsOverviewsUseCase {
    suspend operator fun invoke(): List<UserCollectionOverview>
}

internal class GetCollectionsOverviewsUseCaseImpl @Inject constructor(
    private val repository: CollectionsRepository,
    private val mapper: CollectionsOverviewMapper,
) : GetCollectionsOverviewsUseCase {
    override suspend fun invoke(): List<UserCollectionOverview> =
        mapper.convert(repository.getCollections())
}