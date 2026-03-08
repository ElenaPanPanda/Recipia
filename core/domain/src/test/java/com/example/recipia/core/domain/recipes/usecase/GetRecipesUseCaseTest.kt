package com.example.recipia.core.domain.recipes.usecase

import com.example.recipia.core.domain.recipes.RecipesDomainTestHelper.createShortRecipeDtoList
import com.example.recipia.core.domain.recipes.RecipesDomainTestHelper.createShortRecipeList
import com.example.recipia.core.domain.recipes.mappers.RecipeListMapper
import com.example.recipia.core.network.dto.RecipeListResponse
import com.example.recipia.core.network.repository.RecipeRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetRecipesUseCaseTest {
    private val mockedMapper = mockk<RecipeListMapper>()
    private val mockedRepo = mockk<RecipeRepository>()
    private val getRecipesUseCase = GetRecipesUseCaseImpl(mockedMapper, mockedRepo)

    @Test
    fun getRecipesUseCase_returnsMappedRecipes() = runTest {
        val expectedShortRecipeList = createShortRecipeList()
        val response = RecipeListResponse(createShortRecipeDtoList())

        // Rule: repo should return this response.
        coEvery { mockedRepo.getRecipes() } returns response
        // Rule: mapper should return this data.
        every { mockedMapper.convert(response) } returns expectedShortRecipeList
        // Call usecase and compare the result.
        assertThat(getRecipesUseCase()).isEqualTo(expectedShortRecipeList)
        // Verify that the mapper was called.
        verify(exactly = 1) { mockedMapper.convert(response) }
        // Verify that the usecase calls the repo once.
        coVerify(exactly = 1) { mockedRepo.getRecipes() }
    }
}