package com.example.recipia.feature.recipedetails.impl.domain.mapper

import com.example.recipia.feature.recipedetails.impl.data.dto.GetRecipeResponse
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedRecipe

internal interface RecipeMapper {
    fun convert(response: GetRecipeResponse): DetailedRecipe
}