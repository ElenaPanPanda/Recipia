package com.example.recipia.core.domain.recipes.usecase

import com.example.recipia.core.domain.recipes.RecipesDomainTestHelper.createFullRecipe
import com.example.recipia.core.network.dto.FullRecipeDto
import com.example.recipia.core.network.recipes.RecipeRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AddRecipeUseCaseTest {
    private val mockedRepository = mockk<RecipeRepository>()
    private val addRecipeUseCase = AddRecipeUseCaseImpl(mockedRepository)

    @Test
    fun addRecipeUseCase_returnsRecipeId() = runTest {
        val recipe = createFullRecipe(id = ID)
        val expectedResponse = FullRecipeDto(recipe, "", true)

        coEvery { mockedRepository.addRecipe(recipe) } returns expectedResponse

        val resultId = addRecipeUseCase(recipe)

        assertThat(resultId).isEqualTo(ID)
        coVerify(exactly = 1) { mockedRepository.addRecipe(recipe) }
    }

    private companion object {
        private const val ID = "recipe id"
    }
}