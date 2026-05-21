package com.example.recipia.core.domain.collections.mappers

import com.example.recipia.core.common.model.UserCollectionOverview
import com.example.recipia.core.network.dto.GetCollectionsDto
import javax.inject.Inject

internal interface CollectionOverviewMapper {
    fun convert(response: GetCollectionsDto): List<UserCollectionOverview>
}

internal class CollectionOverviewMapperImpl @Inject constructor() :
    CollectionOverviewMapper {
    override fun convert(response: GetCollectionsDto): List<UserCollectionOverview> {
        with(response) {
            return collections.map { collection ->
                UserCollectionOverview(
                    collectionId = collection.collectionId,
                    collectionName = collection.collectionName,
                    recipesAmount = collection.recipes.size,
                )
            }
        }
    }
}