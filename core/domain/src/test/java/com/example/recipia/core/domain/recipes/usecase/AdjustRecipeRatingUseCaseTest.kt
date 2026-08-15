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

class AdjustRecipeRatingUseCaseTest {
    private val mockedRepo = mockk<RecipeRepository>()
    private val adjustRecipeRatingUseCase = AdjustRecipeRatingUseCaseImpl(mockedRepo)

    @Test
    fun adjustRecipeRatingUseCase_callsRepository() = runTest {
        val rating = 3f
        coEvery { mockedRepo.adjustRecipeRating(ID, rating) } just Runs

        adjustRecipeRatingUseCase(ID, rating)

        coVerify(exactly = 1) { mockedRepo.adjustRecipeRating(ID, rating) }
    }
}