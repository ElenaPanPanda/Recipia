package com.example.recipia.core.domain.recipes

import com.example.recipia.core.common.model.FullRecipe
import com.example.recipia.core.common.model.IngredientSection
import com.example.recipia.core.common.model.RecipeCategory
import com.example.recipia.core.common.model.ShortRecipe
import com.example.recipia.core.network.dto.ShortRecipeDto
import com.example.recipia.core.ui.model.PlaceholderColor

object RecipesDomainTestHelper {
    const val ID = "id"
    private const val ID_1 = "id_1"
    private const val TITLE = "title"
    private const val TITLE_1 = "title_1"
    private const val RATING = 3
    private const val RATING_1 = 4
    private const val URL = "url"
    private const val URL_1 = "url_1"
    private val PLACEHOLDER_COLOR = PlaceholderColor.DARK_RED
    private val PLACEHOLDER_COLOR_1 = PlaceholderColor.DARK_TEAL
    private const val IMAGE_URL = "url"
    private const val INSTRUCTIONS = "instructions"

    private fun createShortRecipeDto(
        id: String = ID,
        title: String = TITLE,
        rating: Int = RATING,
        imageUrl: String = URL,
        placeholderColor: PlaceholderColor = PLACEHOLDER_COLOR,
        rawCategories: List<RecipeCategory> = emptyList()
    ) = ShortRecipeDto(
        id = id,
        title = title,
        rating = rating,
        imageUrl = imageUrl,
        placeholderColor = placeholderColor,
        rawCategories = rawCategories
    )

    private fun createShortRecipe(
        id: String = ID,
        title: String = TITLE,
        rating: Int = RATING,
        imageUrl: String = URL,
        placeholderColor: PlaceholderColor = PLACEHOLDER_COLOR,
        rawCategories: List<RecipeCategory> = emptyList()
    ) = ShortRecipe(
        id = id,
        title = title,
        rating = rating,
        imageUrl = imageUrl,
        placeholderColor = placeholderColor,
        rawCategories = rawCategories
    )

    fun createFullRecipe(
        id: String = ID,
        title: String = TITLE,
        rating: Int = RATING,
        imageUrl: String = IMAGE_URL,
        placeholderColor: PlaceholderColor = PLACEHOLDER_COLOR,
        ingredients: List<IngredientSection> = emptyList(),
        rawCategories: List<RecipeCategory> = emptyList(),
        instructions: String = INSTRUCTIONS
    ) = FullRecipe(
        id = id,
        title = title,
        rating = rating,
        imageUrl = imageUrl,
        placeholderColor = placeholderColor,
        ingredients = ingredients,
        rawCategories = rawCategories,
        instructions = instructions,
    )

    fun createShortRecipeDtoList() = listOf(
        createShortRecipeDto(),
        createShortRecipeDto(ID_1, TITLE_1, RATING_1, URL_1, PLACEHOLDER_COLOR_1)
    )

    fun createShortRecipeList() = listOf(
        createShortRecipe(),
        createShortRecipe(ID_1, TITLE_1, RATING_1, URL_1, PLACEHOLDER_COLOR_1)
    )
}