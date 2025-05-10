package com.example.recipia.feature.recipedetails.impl.domain.mapper

import com.example.recipia.core.common.model.FullRecipe
import com.example.recipia.feature.recipedetails.impl.data.dto.GetRecipeResponse

internal interface RecipeMapper {
    fun convert(response: GetRecipeResponse): FullRecipe
}