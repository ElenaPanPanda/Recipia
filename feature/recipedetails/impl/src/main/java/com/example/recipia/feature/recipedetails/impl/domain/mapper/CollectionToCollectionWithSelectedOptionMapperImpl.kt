package com.example.recipia.feature.recipedetails.impl.domain.mapper

import com.example.recipia.feature.recipedetails.impl.data.dto.GetCollectionsResponse
import com.example.recipia.feature.recipedetails.impl.domain.model.CollectionInRecipeDetails
import javax.inject.Inject

internal class CollectionToCollectionWithSelectedOptionMapperImpl @Inject constructor() :
    CollectionToCollectionWithSelectedOptionMapper {
    override fun convert(response: GetCollectionsResponse): List<CollectionInRecipeDetails> {
        with(response) {
            return collections.map { collection ->
                CollectionInRecipeDetails(
                    collectionId = collection.collectionId,
                    collectionName = collection.collectionName,
                    recipesAmount = collection.recipes.size,
                )
            }
        }
    }
}