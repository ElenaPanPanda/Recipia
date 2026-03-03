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

    private companion object {
        private fun createShortRecipeList() = listOf(
            ShortRecipeDto(
                id = "id",
                title = "title",
                rating = 3,
                imageUrl = "url",
                placeholderColor = PlaceholderColor.DARK_RED,
                rawCategories = emptyList(),
            ),
            ShortRecipeDto(
                id = "id1",
                title = "title1",
                rating = 3,
                imageUrl = "url1",
                placeholderColor = PlaceholderColor.DARK_TEAL,
                rawCategories = emptyList(),
            )
        )
    }
}