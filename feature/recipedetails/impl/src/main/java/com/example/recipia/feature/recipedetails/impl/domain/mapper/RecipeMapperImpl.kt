package com.example.recipia.feature.recipedetails.impl.domain.mapper

import com.example.recipia.core.common.model.Ingredient
import com.example.recipia.core.common.model.IngredientSection
import com.example.recipia.feature.recipedetails.impl.data.dto.GetRecipeResponse
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredient
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredientSection
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedRecipe
import javax.inject.Inject

internal class RecipeMapperImpl @Inject constructor() : RecipeMapper {
    override fun convert(response: GetRecipeResponse): DetailedRecipe {
        fun mapIngredientList(networkItems: List<Ingredient>): List<DetailedIngredient> {
            return networkItems.map { networkIngredient ->
                DetailedIngredient(
                    amount = networkIngredient.amount,
                    ingredient = networkIngredient.ingredient,
                    addedToList = false
                )
            }
        }

        val detailedIngredientSections = response.recipe.ingredients.map { networkSection ->
            when (networkSection) {
                is IngredientSection.Group -> {
                    DetailedIngredientSection.Group(
                        title = networkSection.title,
                        items = mapIngredientList(networkSection.items)
                    )
                }
                is IngredientSection.Ungrouped -> {
                    DetailedIngredientSection.Ungrouped(
                        items = mapIngredientList(networkSection.items)
                    )
                }
            }
        }

        return DetailedRecipe(
            id = response.recipe.id,
            title = response.recipe.title,
            rating = response.recipe.rating,
            imageUrl = response.recipe.imageUrl,
            placeholderColor = response.recipe.placeholderColor,
            ingredients = detailedIngredientSections,
            rawCategories = response.recipe.rawCategories,
            instructions = response.recipe.instructions,
        )
    }
}