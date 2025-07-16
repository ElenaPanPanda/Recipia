package recipea.feature.impl.data.repo

import com.example.recipia.core.ui.model.PlaceholderColor
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import recipia.feature.impl.data.api.RecipeListNetworkApi
import recipia.feature.impl.data.dto.RecipeListResponse
import recipia.feature.impl.data.dto.ShortRecipeDto
import recipia.feature.impl.data.repo.RecipeListRepositoryImpl

class RecipeListRepositoryTest {
    private val mockedApi = mockk<RecipeListNetworkApi>()
    private val repo = RecipeListRepositoryImpl(mockedApi)

    @Test
    fun `getRecipes returns recipes`() = runTest {
        val expectedResponse = RecipeListResponse(
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

        // rule: mocked api should return expected response
        coEvery { mockedApi.getRecipes() } returns expectedResponse

        // call repo and compare the result
        val result = repo.getRecipes()
        assertThat(result).isEqualTo(expectedResponse)

        // check that api was called exactly once
        coVerify(exactly = 1) { mockedApi.getRecipes() }
    }
}