package recipea.feature.recipelist.impl.ui

import com.example.recipia.core.common.model.RecipeCategory
import com.example.recipia.core.common.model.ShortRecipe
import com.example.recipia.core.common.string_res_provider.StringResProvider
import com.example.recipia.core.domain.recipes.usecase.GetRecipesUseCase
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
import recipia.feature.impl.ui.RecipeListEvent
import recipia.feature.impl.ui.RecipeListViewModel

class RecipeListViewModelTest {
    private val mockedStringProvider = mockk<StringResProvider>()
    private val mockedGetRecipesUseCase = mockk<GetRecipesUseCase>()
    private lateinit var viewModel: RecipeListViewModel

    @Before
    fun setUp() {
        every { mockedStringProvider.getString(any()) } returns "Error"
        coEvery { mockedGetRecipesUseCase.getRecipes() } returns RECIPES.reversed()

        viewModel = RecipeListViewModel(mockedStringProvider, mockedGetRecipesUseCase)
    }

    @Test
    fun getRecipes_updatesUiStateCorrectly() = runTest {
        val uiState = viewModel.uiState.value

        val recipes = uiState.recipes

        assertThat(recipes).isEqualTo(RECIPES)
        coVerify(exactly = 1) { mockedGetRecipesUseCase.getRecipes() }
    }

    @Test
    fun onSelectedCategory_filtersRecipesCorrectly() = runTest {
        val category = RecipeCategory.DESSERT
        val expectedFilteredRecipes = listOf(createShortRecipe())

        viewModel.obtainEvent(RecipeListEvent.OnCategorySelected(category))

        val uiState = viewModel.uiState.first()
        assertThat(uiState.selectedCategory).isEqualTo(category)
        assertThat(uiState.filteredRecipes).isEqualTo(expectedFilteredRecipes)
    }

    @Test
    fun onSelectedCategory_withALLCategory_updatesFilteredRecipesCorrectly() = runTest {
        val category = RecipeCategory.ALL

        viewModel.obtainEvent(RecipeListEvent.OnCategorySelected(category))

        val uiState = viewModel.uiState.first()
        assertThat(uiState.selectedCategory).isEqualTo(category)
        assertThat(uiState.filteredRecipes).isEqualTo(RECIPES)
    }

    private companion object {
        val RECIPES = listOf(
            createShortRecipe(),
            createShortRecipe("id1", "title1", 1, "url1", PlaceholderColor.DARK_TEAL, emptyList())
        )

        private fun createShortRecipe(
            id: String = "id",
            title: String = "title",
            rating: Int = 3,
            imageUrl: String = "url",
            placeholderColor: PlaceholderColor = PlaceholderColor.DARK_RED,
            rawCategories: List<RecipeCategory> = listOf(RecipeCategory.DESSERT)
        ) = ShortRecipe(
            id = id,
            title = title,
            rating = rating,
            imageUrl = imageUrl,
            placeholderColor = placeholderColor,
            rawCategories = rawCategories
        )
    }
}