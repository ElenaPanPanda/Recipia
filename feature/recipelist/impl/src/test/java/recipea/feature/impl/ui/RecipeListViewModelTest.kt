package recipea.feature.impl.ui

import com.example.recipia.core.common.model.RecipeCategory
import com.example.recipia.core.common.model.ShortRecipe
import com.example.recipia.core.common.string_res_provider.StringResProvider
import com.example.recipia.core.ui.model.PlaceholderColor
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import recipia.feature.impl.domain.usecase.GetRecipesUseCase
import recipia.feature.impl.ui.RecipeListEvent
import recipia.feature.impl.ui.RecipeListViewModel

class RecipeListViewModelTest {
    private val mockedStringProvider = mockk<StringResProvider>()
    private val mockedGetRecipesUseCase = mockk<GetRecipesUseCase>()
    private lateinit var viewModel: RecipeListViewModel
    private val expectedRecipes = listOf(
        ShortRecipe(
            id = "id",
            title = "title",
            rating = 3,
            imageUrl = "url",
            placeholderColor = PlaceholderColor.DARK_RED,
            rawCategories = listOf(RecipeCategory.DESSERT)
        ),
        ShortRecipe(
            id = "id1",
            title = "title1",
            rating = 3,
            imageUrl = "url1",
            placeholderColor = PlaceholderColor.DARK_TEAL,
            rawCategories = listOf(RecipeCategory.MEAT)
        )
    )

    @Before
    fun setUp() {
        every { mockedStringProvider.getString(any()) } returns "Error"
        coEvery { mockedGetRecipesUseCase.getRecipes() } returns expectedRecipes

        viewModel = RecipeListViewModel(mockedStringProvider, mockedGetRecipesUseCase)
    }

    @Test
    fun `getRecipes should update UI state correctly`() = runTest {
        val uiState = viewModel.uiState.value

        val recipes = uiState.recipes
        assertThat(recipes).isEqualTo(expectedRecipes)

        coVerify(exactly = 1) { mockedGetRecipesUseCase.getRecipes() }
    }

    @Test
    fun `onSelectedCategory updates filteredRecipes correctly`() = runTest {
        // passed category
        val category = RecipeCategory.DESSERT
        val expectedFilteredRecipes = listOf(
            ShortRecipe(
                id = "id",
                title = "title",
                rating = 3,
                imageUrl = "url",
                placeholderColor = PlaceholderColor.DARK_RED,
                rawCategories = listOf(RecipeCategory.DESSERT)
            )
        )
        // call the function
        viewModel.obtainEvent(RecipeListEvent.OnCategorySelected(category))

        val uiState = viewModel.uiState.first()

        assertThat(uiState.selectedCategory).isEqualTo(category)
        assertThat(uiState.filteredRecipes).isEqualTo(expectedFilteredRecipes)
    }

    @Test
    fun `onSelectedCategory with ALL category updates filteredRecipes correctly`() = runTest {
        // passed category
        val category = RecipeCategory.ALL
        // call the function
        viewModel.obtainEvent(RecipeListEvent.OnCategorySelected(category))
        val uiState = viewModel.uiState.first()

        assertThat(uiState.selectedCategory).isEqualTo(category)
        assertThat(uiState.filteredRecipes).isEqualTo(expectedRecipes)
    }
}