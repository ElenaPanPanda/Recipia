package recipea.feature.impl.domain.usecase

import com.example.recipia.core.common.model.ShortRecipe
import com.example.recipia.core.ui.model.PlaceholderColor
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Test
import recipia.feature.impl.data.dto.RecipeListResponse
import recipia.feature.impl.data.dto.ShortRecipeDto
import recipia.feature.impl.data.repo.RecipeListRepository
import recipia.feature.impl.domain.mapper.RecipeListMapper
import recipia.feature.impl.domain.usecase.GetRecipesUseCaseImpl

class GetRecipesUseCaseTest {
    // mock
    private val mockedMapper = mockk<RecipeListMapper>()
    private val mockedRepo = mockk<RecipeListRepository>()

    private val useCase = GetRecipesUseCaseImpl(mockedMapper, mockedRepo)

    @Test
    fun `useCase returns mapped recipes`() = runTest {
        // expected response
        val response = RecipeListResponse(
            recipes = listOf(
                ShortRecipeDto(
                    id = "id",
                    title = "title",
                    rating = 3,
                    imageUrl = "url",
                    placeholderColor = PlaceholderColor.DARK_RED,
                    rawCategories = emptyList(),
                ),
                ShortRecipeDto(
                    id = "id1",
                    title = "title1",
                    rating = 3,
                    imageUrl = "url1",
                    placeholderColor = PlaceholderColor.DARK_TEAL,
                    rawCategories = emptyList(),
                )
            )
        )

        // expected mapped recipes
        val expected = listOf(
            ShortRecipe(
                id = "id",
                title = "title",
                rating = 3,
                imageUrl = "url",
                placeholderColor = PlaceholderColor.DARK_RED,
            ),
            ShortRecipe(
                id = "id1",
                title = "title1",
                rating = 3,
                imageUrl = "url1",
                placeholderColor = PlaceholderColor.DARK_TEAL,
            )
        )

        // rule: repo should return this response
        coEvery { mockedRepo.getRecipes() } returns response
        // rule: mapper should return this data
        every { mockedMapper.convert(response) } returns expected

        // call usecase and compare the result
        val result = useCase.getRecipes()
        assertThat(result).isEqualTo(expected)

        // verify that the mapper was called
        verify(exactly = 1) { mockedMapper.convert(response) }

        // verify that the usecase calls the repo once
        coVerify(exactly = 1) { mockedRepo.getRecipes() }
    }
}