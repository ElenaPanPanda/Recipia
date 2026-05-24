package com.example.recipia.core.domain.collections.usecase

import com.example.recipia.core.common.model.UserCollection
import com.example.recipia.core.domain.collections.mappers.UserCollectionMapper
import com.example.recipia.core.network.collections.CollectionsRepository
import javax.inject.Inject

interface GetCollectionByIdUseCase {
    suspend operator fun invoke(id: String): UserCollection
}

internal class GetCollectionByIdUseCaseImpl @Inject constructor(
    private val repository: CollectionsRepository,
    private val mapper: UserCollectionMapper
) : GetCollectionByIdUseCase {
    override suspend fun invoke(id: String): UserCollection =
        mapper.convert(repository.getCollectionById(id))
}