package com.example.recipia.core.domain.recipes.usecase

import com.example.recipia.core.common.model.FullRecipe
import com.example.recipia.core.network.dto.FullRecipeResponse
import com.example.recipia.core.network.repository.RecipeRepository
import com.example.recipia.core.ui.model.PlaceholderColor
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AddRecipeUseCaseTest {
    private val mockedRepository = mockk<RecipeRepository>()
    private val useCase = AddRecipeUseCaseImpl(mockedRepository)

    @Test
    fun addRecipe_returnsRecipeId() = runTest {
        val recipe = createFullRecipe()
        val expectedResponse = FullRecipeResponse(recipe, "", true)

        coEvery { mockedRepository.addRecipe(recipe) } returns expectedResponse

        val resultId = useCase.addRecipe(recipe)

        assertThat(resultId).isEqualTo(ID)
        coVerify(exactly = 1) { mockedRepository.addRecipe(recipe) }
    }

    private companion object {
        private const val ID = "id"
        private fun createFullRecipe() =
            FullRecipe(
                id = ID,
                title = "title",
                rating = 1,
                imageUrl = "url",
                placeholderColor = PlaceholderColor.LIGHT_TEAL,
                ingredients = emptyList(),
                rawCategories = emptyList(),
                instructions = "",
            )
    }
}