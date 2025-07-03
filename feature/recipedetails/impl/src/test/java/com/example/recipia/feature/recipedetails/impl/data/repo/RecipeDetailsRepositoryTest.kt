package com.example.recipia.feature.recipedetails.impl.data.repo

import com.example.recipia.core.common.model.FullRecipe
import com.example.recipia.core.ui.model.PlaceholderColor
import com.example.recipia.feature.recipedetails.impl.data.api.RecipeDetailsNetworkApi
import com.example.recipia.feature.recipedetails.impl.data.dto.GetRecipeResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class RecipeDetailsRepositoryTest {
    private val mockedApi = mockk<RecipeDetailsNetworkApi>()
    private val repo = RecipeDetailsRepositoryImpl(mockedApi)

    @Test
    fun `getRecipe returns recipe`() = runTest {
        // given id
        val id = "id"

        // expected result
        val expectedRecipe = GetRecipeResponse(
            recipe = FullRecipe(
                id = "id",
                title = "title",
                rating = 3,
                imageUrl = "url",
                placeholderColor = PlaceholderColor.DARK_RED,
                ingredients = emptyList(),
                instructions = "instructions",
                rawCategories = emptyList()
            ),
            message = "message",
            success = true
        )

        // rule: mocked api should return expected recipe
        coEvery { mockedApi.getRecipe(id) } returns expectedRecipe

        // call repo and compare the result
        val result = repo.getRecipe(id)
        assert(result == expectedRecipe)

        // check that api was called exactly once
        coVerify(exactly = 1) { mockedApi.getRecipe(id) }
    }
}