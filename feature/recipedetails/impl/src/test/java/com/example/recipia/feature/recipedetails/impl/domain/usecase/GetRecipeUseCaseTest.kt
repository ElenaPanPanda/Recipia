package com.example.recipia.feature.recipedetails.impl.domain.usecase

import com.example.recipia.core.common.model.FullRecipe
import com.example.recipia.core.ui.model.PlaceholderColor
import com.example.recipia.feature.recipedetails.impl.data.dto.GetRecipeResponse
import com.example.recipia.feature.recipedetails.impl.data.repo.RecipeDetailsRepository
import com.example.recipia.feature.recipedetails.impl.domain.mapper.RecipeToDetailedMapper
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedRecipe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetRecipeUseCaseTest {
    // mock
    private val repo = mockk<RecipeDetailsRepository>()
    private val mapper = mockk<RecipeToDetailedMapper>()

    private val useCase = GetRecipeUseCaseImpl(mapper, repo)

    @Test
    fun `returns mapped ingredients`() = runTest {
        val givenId = "id"

        // expected repo response
        val givenRecipe = GetRecipeResponse(
            recipe = FullRecipe(
                id = "id",
                title = "title",
                rating = 3,
                imageUrl = "url",
                placeholderColor = PlaceholderColor.DARK_RED,
                ingredients = emptyList(),
                instructions = "instructions",
                rawCategories = emptyList(),
            ),
            message = "message",
            success = true
        )

        // expected usecase response
        val expectedRecipe = DetailedRecipe(
            id = "id",
            title = "title",
            rating = 3,
            imageUrl = "url",
            placeholderColor = PlaceholderColor.DARK_RED,
            ingredients = emptyList(),
            instructions = "instructions",
            rawCategories = emptyList(),
        )

        // rule: repo should return this recipe when this id was passed
        coEvery { repo.getRecipe(givenId) } returns givenRecipe
        // rule: mapper should return this recipe
        every { mapper.convert(givenRecipe) } returns expectedRecipe

        // call usecase and compare the result
        val result = useCase.getRecipe(givenId)
        assertEquals(expectedRecipe, result)

        // verify that the mapper was called once
        verify(exactly = 1) { mapper.convert(givenRecipe) }

        // verify that the usecase calls the repo once
        coVerify(exactly = 1) { repo.getRecipe(givenId) }
    }
}