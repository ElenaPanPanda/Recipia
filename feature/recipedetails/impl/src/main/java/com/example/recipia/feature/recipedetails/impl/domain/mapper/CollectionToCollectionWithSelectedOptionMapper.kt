package com.example.recipia.feature.recipedetails.impl.domain.mapper

import com.example.recipia.feature.recipedetails.impl.data.dto.GetCollectionsResponse
import com.example.recipia.feature.recipedetails.impl.domain.model.CollectionInRecipeDetails

internal interface CollectionToCollectionWithSelectedOptionMapper {
    fun convert(response: GetCollectionsResponse): List<CollectionInRecipeDetails>
}