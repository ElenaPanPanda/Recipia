package com.example.recipia.feature.recipedetails.impl.domain.mapper

import com.example.recipia.core.common.model.FullRecipe
import com.example.recipia.core.common.model.Ingredient
import com.example.recipia.core.common.model.IngredientSection
import com.example.recipia.core.common.model.RecipeCategory
import com.example.recipia.core.ui.model.PlaceholderColor
import com.example.recipia.feature.recipedetails.impl.data.dto.GetRecipeResponse
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredient
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredientSection
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedRecipe
import org.junit.Assert.assertEquals
import org.junit.Test

class RecipeToDetailedMapperTest {
    private val mapper = RecipeToDetailedMapperImpl()

    @Test
    fun convertRecipeToDetailedRecipeTest() {
        val response = GetRecipeResponse(
            recipe = FullRecipe(
                id = "id",
                title = "title",
                rating = 4,
                imageUrl = "imageUrl",
                placeholderColor = PlaceholderColor.DARK_RED,
                ingredients = listOf(
                    IngredientSection(
                        title = "title",
                        ingredientsList = listOf(
                            Ingredient(amount = "amount1", name = "name1"),
                            Ingredient(amount = "amount2", name = "name2"),
                        )
                    ),
                    IngredientSection(
                        title = "title",
                        ingredientsList = listOf(Ingredient(amount = "amount1", name = "name1")),
                    )
                ),
                rawCategories = listOf(RecipeCategory.DRINKS, RecipeCategory.DESSERT),
                instructions = "instructions",
            ),
            message = "message",
            success = true
        )

        val expected = DetailedRecipe(
            id = "id",
            title = "title",
            rating = 4,
            imageUrl = "imageUrl",
            placeholderColor = PlaceholderColor.DARK_RED,
            ingredients = listOf(
                DetailedIngredientSection(
                    title = "title",
                    ingredientsList = listOf(
                        DetailedIngredient(
                            amount = "amount1",
                            ingredient = "name1",
                            addedToList = false
                        ),
                        DetailedIngredient(
                            amount = "amount2",
                            ingredient = "name2",
                            addedToList = false
                        ),
                    )
                ),
                DetailedIngredientSection(
                    title = "title",
                    ingredientsList = listOf(
                        DetailedIngredient(
                            amount = "amount1",
                            ingredient = "name1",
                            addedToList = false
                        ),
                    )
                )
            ),
            rawCategories = listOf(RecipeCategory.DRINKS, RecipeCategory.DESSERT),
            instructions = "instructions",
        )

        assertEquals(expected, mapper.convert(response))
    }
}