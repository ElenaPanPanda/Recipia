package com.example.recipia.core.network.repository

import com.example.recipia.core.network.api.RecipesNetworkApi
import com.example.recipia.core.network.dto.RecipeListResponse
import com.example.recipia.core.network.dto.ShortRecipeDto
import com.example.recipia.core.ui.model.PlaceholderColor
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import com.example.recipia.core.common.model.FullRecipe
import com.example.recipia.core.network.dto.FullRecipeResponse

class RecipeRepositoryTest {
    private val mockedApi = mockk<RecipesNetworkApi>()
    private val repo = RecipeRepositoryImpl(mockedApi)

    @Test
    fun getRecipes_returnsRecipes() = runTest {
        val expectedResponse = RecipeListResponse(createShortRecipeList())

        // Rule: mocked api should return expected response.
        coEvery { mockedApi.getRecipes() } returns expectedResponse
        // Call repo and compare the result.
        assertThat(repo.getRecipes()).isEqualTo(expectedResponse)
        // Check that api was called exactly once.
        coVerify(exactly = 1) { mockedApi.getRecipes() }
    }

    @Test
    fun addRecipe_returnsFullRecipeResponse() = runTest {
        val recipe = createFullRecipe()
        val expectedResponse = FullRecipeResponse(recipe, "", true)

        // Rule: mocked api should return expected response.
        coEvery { mockedApi.addRecipe(recipe) } returns expectedResponse
        // Call repo and compare the result.
        assertThat(repo.addRecipe(recipe)).isEqualTo(expectedResponse)
        // Check that api was called exactly once.
        coVerify(exactly = 1) { mockedApi.addRecipe(recipe) }
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

        private fun createShortRecipeList() = listOf(
            ShortRecipeDto(
                id = ID,
                title = TITLE,
                rating = RATING,
                imageUrl = URL,
                placeholderColor = PLACEHOLDER_COLOR,
                rawCategories = emptyList(),
            ),
            ShortRecipeDto(
                id = ID_1,
                title = TITLE_1,
                rating = RATING_1,
                imageUrl = URL_1,
                placeholderColor = PLACEHOLDER_COLOR_1,
                rawCategories = emptyList(),
            )
        )

        private fun createFullRecipe() =
            FullRecipe(
                id = ID,
                title = TITLE,
                rating = RATING,
                imageUrl = URL,
                placeholderColor = PLACEHOLDER_COLOR,
                ingredients = emptyList(),
                rawCategories = emptyList(),
                instructions = "",
            )
    }
}