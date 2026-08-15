package com.example.recipia.core.domain.recipes.usecase

import com.example.recipia.core.domain.recipes.RecipesDomainTestHelper.ID
import com.example.recipia.core.network.recipes.RecipeRepository
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DeleteRecipeUseCaseTest {
    private val mockedRepo = mockk<RecipeRepository>()
    private val deleteRecipeUseCase = DeleteRecipeUseCaseImpl(mockedRepo)

    @Test
    fun deleteRecipeUseCase_callsRepository() = runTest {
        coEvery { mockedRepo.deleteRecipe(ID) } just Runs

        deleteRecipeUseCase(ID)

        coVerify(exactly = 1) { mockedRepo.deleteRecipe(ID) }
    }

    @Test
    fun deleteRecipeUseCase_withEmptyId_callsRepository() = runTest {
        val emptyId = ""
        coEvery { mockedRepo.deleteRecipe(emptyId) } just Runs

        deleteRecipeUseCase(emptyId)

        coVerify(exactly = 1) { mockedRepo.deleteRecipe(emptyId) }
    }
}