package com.example.recipia.feature.recipedetails.impl.domain.mapper

import com.example.recipia.feature.recipedetails.impl.data.dto.GetRecipeResponse
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredient
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredientSection
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedRecipe
import javax.inject.Inject

internal class RecipeMapperImpl @Inject constructor() : RecipeMapper {
    override fun convert(response: GetRecipeResponse): DetailedRecipe {
        with(response) {
            return DetailedRecipe(
                id = recipe.id,
                title = recipe.title,
                rating = recipe.rating,
                imageUrl = recipe.imageUrl,
                placeholderColor = recipe.placeholderColor,
                ingredients = recipe.ingredients.map { ingredientSection ->
                   DetailedIngredientSection(
                       title = ingredientSection.title,
                       ingredientsList = ingredientSection.ingredientsList.map { ingredient ->
                           DetailedIngredient(
                               amount = ingredient.amount,
                               ingredient = ingredient.name,
                               addedToList = false,
                           )
                       }
                   )
                },
                rawCategories = recipe.rawCategories,
                instructions = recipe.instructions,
            )
        }
    }
}