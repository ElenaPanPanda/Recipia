package com.example.recipia.feature.recipedetails.impl.domain.mapper

import com.example.recipia.feature.recipedetails.impl.data.dto.GetCollectionsResponse
import com.example.recipia.feature.recipedetails.impl.domain.model.CollectionWithSelectedOption
import javax.inject.Inject

internal class CollectionToCollectionWithSelectedOptionMapperImpl @Inject constructor() :
    CollectionToCollectionWithSelectedOptionMapper {
    override fun convert(response: GetCollectionsResponse): List<CollectionWithSelectedOption> {
        with(response) {
            return collections.map { collection ->
                CollectionWithSelectedOption(
                    collectionId = collection.collectionId,
                    collectionName = collection.collectionName,
                    recipes = collection.recipes,
                    isSelected = false,
                    collectionColor = collection.collectionColor
                )
            }
        }
    }
}