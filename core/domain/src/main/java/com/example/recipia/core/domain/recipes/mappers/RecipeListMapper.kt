package com.example.recipia.core.domain.recipes.mappers

import com.example.recipia.core.common.model.ShortRecipe
import com.example.recipia.core.network.dto.RecipeListDto
import javax.inject.Inject

internal interface RecipeListMapper {
    fun convert(response: RecipeListDto): List<ShortRecipe>
}

internal class RecipeListMapperImpl @Inject constructor() : RecipeListMapper {
    override fun convert(response: RecipeListDto): List<ShortRecipe> {
        with(response) {
            return recipes.map { recipe ->
                ShortRecipe(
                    id = recipe.id,
                    title = recipe.title,
                    rating = recipe.rating,
                    imageUrl = recipe.imageUrl,
                    placeholderColor = recipe.placeholderColor,
                    rawCategories = recipe.rawCategories,
                )
            }
        }
    }
}