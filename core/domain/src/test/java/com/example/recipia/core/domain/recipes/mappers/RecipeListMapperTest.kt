package com.example.recipia.core.domain.recipes.mappers

import com.example.recipia.core.common.model.RecipeCategory
import com.example.recipia.core.common.model.ShortRecipe
import com.example.recipia.core.network.dto.RecipeListResponse
import com.example.recipia.core.network.dto.ShortRecipeDto
import com.example.recipia.core.ui.model.PlaceholderColor
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RecipeListMapperTest {
    private val mapper = RecipeListMapperImpl()

    @Test
    fun mapper_convertsResponseCorrectly() {
        val response = RecipeListResponse(
            recipes = listOf(
                createShortRecipeDto(),
                createShortRecipeDto(ID_1, TITLE_1, RATING_1, URL_1, PLACEHOLDER_COLOR_1)
            )
        )
        val expected = listOf(
            createShortRecipe(),
            createShortRecipe(ID_1, TITLE_1, RATING_1, URL_1, PLACEHOLDER_COLOR_1)
        )

        val result = mapper.convert(response)

        assertThat(result).isEqualTo(expected)
    }

    private companion object {
        private const val ID = "id"
        private const val ID_1 = "id_1"
        private const val TITLE = "title"
        private const val TITLE_1 = "title_1"
        private const val RATING = 3
        private const val RATING_1 = 4
        private const val URL = "url"
        private const val URL_1 = "url_1"
        private val PLACEHOLDER_COLOR = PlaceholderColor.DARK_RED
        private val PLACEHOLDER_COLOR_1 = PlaceholderColor.DARK_TEAL

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
    }
}