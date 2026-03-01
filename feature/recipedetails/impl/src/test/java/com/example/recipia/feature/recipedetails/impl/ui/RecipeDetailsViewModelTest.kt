package com.example.recipia.feature.recipedetails.impl.ui

import androidx.lifecycle.SavedStateHandle
import com.example.recipia.core.common.string_res_provider.StringResProvider
import com.example.recipia.core.ui.model.PlaceholderColor
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredient
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredientSection
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedRecipe
import com.example.recipia.feature.recipedetails.impl.domain.usecase.AddAllIngredientsToShoppingListUseCase
import com.example.recipia.feature.recipedetails.impl.domain.usecase.CheckAddedIngredientsInShoppingListUseCase
import com.example.recipia.feature.recipedetails.impl.domain.usecase.GetRecipeUseCase
import com.example.recipia.feature.recipedetails.impl.domain.usecase.UpdateShoppingListUseCase
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class RecipeDetailsViewModelTest {

    private val stringProvider = mockk<StringResProvider>(relaxed = true)
    private val savedStateHandle = mockk<SavedStateHandle>(relaxed = true)
    private val getRecipeUseCase = mockk<GetRecipeUseCase>()
    private val checkAddedIngredientsInShoppingListUseCase = mockk<CheckAddedIngredientsInShoppingListUseCase>()
    private val addAllIngredientsToShoppingListUseCase = mockk<AddAllIngredientsToShoppingListUseCase>()
    private val updateShoppingListUseCase = mockk<UpdateShoppingListUseCase>()

    private lateinit var viewModel: RecipeDetailsViewModel

    private val testRecipe = DetailedRecipe(
        id = "id",
        title = "Test Recipe",
        rating = 3,
        imageUrl = "url",
        placeholderColor = PlaceholderColor.DARK_RED,
        ingredients = emptyList(),
        instructions = "instructions",
        rawCategories = emptyList(),
    )

    private val detailedIngredient = DetailedIngredient("1 cup", "Tomato", true)

    @Before
    fun setUp() {
        every { stringProvider.getString(any()) } returns "Recipe not found"
        every { savedStateHandle.get<String>("recipeId") } returns "id"

        coEvery { getRecipeUseCase.getRecipe("id") } returns testRecipe
        coEvery { checkAddedIngredientsInShoppingListUseCase.getAddedIngredients("Test Recipe") } returns flowOf(listOf(detailedIngredient))
        coEvery { addAllIngredientsToShoppingListUseCase.add(any(), any()) } just Runs
        coEvery { updateShoppingListUseCase.update(any(), any()) } just Runs

        viewModel = RecipeDetailsViewModel(
            stringProvider = stringProvider,
            savedStateHandle = savedStateHandle,
            getRecipeUseCase = getRecipeUseCase,
            checkAddedIngredientsInShoppingListUseCase = checkAddedIngredientsInShoppingListUseCase,
            addAllIngredientsToShoppingListUseCase = addAllIngredientsToShoppingListUseCase,
            updateShoppingListUseCase = updateShoppingListUseCase
        )
    }

    @Test
    fun `loadRecipe updates UI state correctly`() = runTest {
        val uiState = viewModel.uiState.value
        assertTrue(uiState is RecipeDetailsState.Success)

        val recipe = (uiState as RecipeDetailsState.Success).recipe
        assertEquals("Test Recipe", recipe.title)

        coVerify(exactly = 1) { getRecipeUseCase.getRecipe("id") }
    }

    @Test
    fun `addAllIngredientsToShoppingList invokes use case`() = runTest {
        val recipeName = "Test Recipe"
        val ingredients = listOf(DetailedIngredientSection("Section", listOf(detailedIngredient)))
        viewModel.addAllIngredientsToShoppingList(recipeName, ingredients)

        coVerify(exactly = 1) { addAllIngredientsToShoppingListUseCase.add(recipeName, ingredients) }
    }
}