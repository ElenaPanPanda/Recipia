package com.example.recipia.core.domain.recipes.usecase

import com.example.recipia.core.domain.recipes.RecipesDomainTestHelper.createShortRecipeDtoList
import com.example.recipia.core.domain.recipes.RecipesDomainTestHelper.createShortRecipeList
import com.example.recipia.core.domain.recipes.mappers.RecipeListMapper
import com.example.recipia.core.network.dto.RecipeListDto
import com.example.recipia.core.network.recipes.RecipeRepository
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
        val response = RecipeListDto(createShortRecipeDtoList())
        coEvery { mockedRepo.getRecipes() } returns response
        every { mockedMapper.convert(response) } returns expectedShortRecipeList

        val recipes = getRecipesUseCase()

        assertThat(recipes).isEqualTo(expectedShortRecipeList)
        coVerify(exactly = 1) { mockedRepo.getRecipes() }
        verify(exactly = 1) { mockedMapper.convert(response) }
    }
}