package com.example.recipia.core.domain.collections.mappers

import com.example.recipia.core.common.model.UserCollectionOverview
import com.example.recipia.core.network.dto.GetCollectionsDto
import javax.inject.Inject

internal interface CollectionsOverviewMapper {
    fun convert(response: GetCollectionsDto): List<UserCollectionOverview>
}

internal class CollectionsOverviewMapperImpl @Inject constructor() :
    CollectionsOverviewMapper {
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