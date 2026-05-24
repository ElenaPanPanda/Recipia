package recipea.feature.addrecipe.impl.ui

import com.example.recipia.core.common.model.RecipeCategory
import com.example.recipia.core.common.string_res_provider.StringResProvider
import com.example.recipia.core.domain.recipes.usecase.AddRecipeUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import recipia.feature.add_recipe.impl.model.CategoryForChoose
import recipia.feature.add_recipe.impl.ui.AddRecipeEvent
import recipia.feature.add_recipe.impl.ui.AddRecipeViewModel

class AddRecipeViewModelTest {
    private val mockedStringProvider = mockk<StringResProvider>()
    private val mockedAddRecipeUseCase = mockk<AddRecipeUseCase>()
    private lateinit var viewModel: AddRecipeViewModel

    @Before
    fun setUp() {
        every { mockedStringProvider.getString(any()) } returns "Error"
        viewModel = AddRecipeViewModel(mockedStringProvider, mockedAddRecipeUseCase)
    }

    @Test
    fun onTitleInputChanged_updatesUiStateCorrectly() = runTest {
        val title = "New Recipe"

        viewModel.obtainEvent(AddRecipeEvent.OnTitleInputChanged(title))

        val uiState = viewModel.uiState.value
        assertThat(uiState.titleInput).isEqualTo(title)
        assertThat(uiState.enabledSaveButton).isTrue()
        assertThat(uiState.titleInputErrorState).isFalse()
    }

    @Test
    fun onImageUrlInputChanged_updatesUiStateCorrectly() = runTest {
        val url = "http://image.url"

        viewModel.obtainEvent(AddRecipeEvent.OnImageUrlInputChanged(url))

        val uiState = viewModel.uiState.value
        assertThat(uiState.imageUrlInput).isEqualTo(url)
    }

    @Test
    fun onCategorySelected_updatesUiStateCorrectly() = runTest {
        val category = RecipeCategory.DESSERT
        val categoryForChoose = CategoryForChoose(category, false)

        viewModel.obtainEvent(AddRecipeEvent.OnCategorySelected(categoryForChoose))

        val uiState = viewModel.uiState.value
        val updatedCategory = uiState.categories.find { it?.category == category }
        assertThat(updatedCategory?.isSelected).isTrue()
    }

    @Test
    fun onAddIngredientClicked_addsEmptyIngredientToGroup() = runTest {
        val initialIngredientsCount = viewModel.uiState.value.ingredients[0].ingredientsList.size

        viewModel.obtainEvent(AddRecipeEvent.OnAddIngredientClicked(0))

        val uiState = viewModel.uiState.value
        val newIngredientsList = uiState.ingredients[0].ingredientsList
        assertThat(newIngredientsList.size).isEqualTo(initialIngredientsCount + 1)
        assertThat(newIngredientsList.last().name).isEmpty()
        assertThat(newIngredientsList.last().amount).isEmpty()
    }

    @Test
    fun onSaveDisabledClicked_showsEmptyTitleError() = runTest {
        viewModel.obtainEvent(AddRecipeEvent.OnSaveDisabledClicked)

        val uiState = viewModel.uiState.value
        assertThat(uiState.titleInputErrorState).isTrue()
        assertThat(uiState.titleInputErrorText).isEqualTo("Error")
    }

    @Test
    fun onSaveEnabledClicked_callsAddRecipeUseCase() = runTest {
        val expectedNewId = "new_recipe_id"
        coEvery { mockedAddRecipeUseCase.addRecipe(any()) } returns expectedNewId

        viewModel.obtainEvent(AddRecipeEvent.OnTitleInputChanged("Title"))
        viewModel.obtainEvent(AddRecipeEvent.OnSaveEnabledClicked)

        coVerify(exactly = 1) { mockedAddRecipeUseCase.addRecipe(any()) }
    }
}