package com.example.recipia.feature.recipedetails.impl.domain.usecase

import com.example.recipia.feature.recipedetails.impl.data.repo.RecipeDetailsRepository
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class DeleteRecipeUseCaseTest {

    private val repository = mockk<RecipeDetailsRepository>()
    private lateinit var useCase: DeleteRecipeUseCase

    @Before
    fun setUp() {
        useCase = DeleteRecipeUseCaseImpl(repository)
    }

    @Test
    fun `delete invokes repository deleteRecipe`() = runTest {
        val recipeId = "12345"
        coEvery { repository.deleteRecipe(recipeId) } just Runs

        useCase.delete(recipeId)

        coVerify(exactly = 1) { repository.deleteRecipe(recipeId) }
    }
}
