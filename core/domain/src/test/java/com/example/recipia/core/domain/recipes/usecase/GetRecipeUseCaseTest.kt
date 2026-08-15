package com.example.recipia.core.domain.recipes.usecase

import com.example.recipia.core.domain.recipes.RecipesDomainTestHelper.ID
import com.example.recipia.core.domain.recipes.RecipesDomainTestHelper.createFullRecipe
import com.example.recipia.core.network.recipes.RecipeRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetRecipeUseCaseTest {
    private val mockedRepo = mockk<RecipeRepository>()
    private val getRecipeUseCase = GetRecipeUseCaseImpl(mockedRepo)

    @Test
    fun getRecipeUseCase_returnsRecipe() = runTest {
        val expectedRecipe = createFullRecipe()
        coEvery { mockedRepo.getRecipe(ID).recipe } returns expectedRecipe

        val recipe = getRecipeUseCase(ID)

        coVerify(exactly = 1) { mockedRepo.getRecipe(ID) }
        assertThat(recipe).isEqualTo(expectedRecipe)
    }
}