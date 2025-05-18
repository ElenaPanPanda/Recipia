package recipia.feature.add_recipe.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipia.core.common.model.FullRecipe
import com.example.recipia.core.common.model.Ingredient
import com.example.recipia.core.common.model.IngredientSection
import com.example.recipia.core.common.string_res_provider.StringResProvider
import com.example.recipia.core.ui.R as uiR
import com.example.recipia.core.ui.model.PlaceholderColor
import com.example.recipia.feature.addrecipe.impl.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import recipia.feature.add_recipe.impl.domain.model.CategoryForChoose
import recipia.feature.add_recipe.impl.domain.usecase.AddRecipeUseCase
import javax.inject.Inject

@HiltViewModel
class AddRecipeViewModel @Inject constructor(
    private val stringProvider: StringResProvider,
    private val addRecipeUseCase: AddRecipeUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(AddRecipeState())
    val uiState: StateFlow<AddRecipeState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<AddRecipeEffect>()
    val uiEffect: SharedFlow<AddRecipeEffect> = _uiEffect.asSharedFlow()

    fun obtainEvent(event: AddRecipeEvent) {
        when (event) {
            is AddRecipeEvent.OnTitleInputChanged -> changeTitleInput(event.value)
            is AddRecipeEvent.OnCategorySelected -> changeCategoriesState(event.category)
            is AddRecipeEvent.OnIngredientTitleValueChange ->
                changeIngredientTitleValue(event.value, event.index)

            is AddRecipeEvent.OnIngredientNameValueChange -> changeIngredientNameValue(
                event.value,
                event.ingredientsGroupIndex,
                event.ingredientIndex
            )

            is AddRecipeEvent.OnIngredientAmountValueChange -> changeIngredientAmountValue(
                event.value,
                event.ingredientsGroupIndex,
                event.ingredientIndex
            )

            is AddRecipeEvent.OnIngredientRemoveClicked -> removeIngredient(
                event.ingredientsGroupIndex,
                event.ingredientIndex
            )

            is AddRecipeEvent.OnAddIngredientClicked -> addIngredient(event.ingredientsGroupIndex)
            is AddRecipeEvent.OnRemoveIngredientsCardClicked -> removeIngredientsCard(event.ingredientsGroupIndex)
            is AddRecipeEvent.OnAddIngredientsGroupClicked -> addIngredientsGroup()
            is AddRecipeEvent.OnInstructionsInputChanged -> changeInstructionsInput(event.value)
            is AddRecipeEvent.OnSaveEnabledClicked -> saveRecipe()
            is AddRecipeEvent.OnSaveDisabledClicked -> showEmptyTitleError()
            is AddRecipeEvent.OpenExitDialog -> showExitDialog()
        }
    }

    private fun changeTitleInput(value: String) {
        _uiState.update {
            it.copy(
                titleInput = value,
                enabledSaveButton = value.isNotEmpty(),
                titleInputErrorState = false,
                titleInputErrorText = null
            )
        }
    }

    private fun changeCategoriesState(category: CategoryForChoose) {
        val updatedCategories = _uiState.value.categories.map {
            if (it?.category == category.category) {
                it.copy(isSelected = !it.isSelected)
            } else {
                it?.copy()
            }
        }
        _uiState.update { it.copy(categories = updatedCategories) }
    }

    private fun changeIngredientTitleValue(value: String, index: Int) {
        val updatedIngredients = _uiState.value.ingredients.toMutableList()
        updatedIngredients[index] = updatedIngredients[index].copy(title = value)
        _uiState.update { it.copy(ingredients = updatedIngredients) }
    }

    private fun changeIngredientNameValue(
        value: String,
        ingredientsGroupIndex: Int,
        ingredientIndex: Int
    ) {
        val updatedIngredients = _uiState.value.ingredients.toMutableList()
        val updatedIngredientSection = updatedIngredients[ingredientsGroupIndex]
        updatedIngredients[ingredientsGroupIndex] = updatedIngredientSection.copy(
            ingredientsList = updatedIngredientSection.ingredientsList.mapIndexed { index, ingredient ->
                if (index == ingredientIndex) {
                    ingredient.copy(name = value)
                } else {
                    ingredient.copy()
                }
            }
        )
        _uiState.update { it.copy(ingredients = updatedIngredients) }
    }

    private fun changeIngredientAmountValue(
        value: String,
        ingredientsGroupIndex: Int,
        ingredientIndex: Int
    ) {
        val updatedIngredients = _uiState.value.ingredients.toMutableList()
        val updatedIngredientSection = updatedIngredients[ingredientsGroupIndex]
        updatedIngredients[ingredientsGroupIndex] = updatedIngredientSection.copy(
            ingredientsList = updatedIngredientSection.ingredientsList.mapIndexed { index, ingredient ->
                if (index == ingredientIndex) {
                    ingredient.copy(amount = value)
                } else {
                    ingredient.copy()
                }
            }
        )
        _uiState.update { it.copy(ingredients = updatedIngredients) }
    }

    private fun removeIngredient(ingredientsGroupIndex: Int, ingredientIndex: Int) {
        val updatedIngredients = _uiState.value.ingredients.toMutableList()
        val updatedIngredientSection = updatedIngredients[ingredientsGroupIndex]
        updatedIngredients[ingredientsGroupIndex] = updatedIngredientSection.copy(
            ingredientsList = updatedIngredientSection.ingredientsList.filterIndexed { index, _ ->
                index != ingredientIndex
            }
        )
        _uiState.update { it.copy(ingredients = updatedIngredients) }
    }

    private fun addIngredient(ingredientsGroupIndex: Int) {
        val updatedIngredients = _uiState.value.ingredients.toMutableList()
        val updatedIngredientSection = updatedIngredients[ingredientsGroupIndex]
        updatedIngredients[ingredientsGroupIndex] = updatedIngredientSection.copy(
            ingredientsList = updatedIngredientSection.ingredientsList.toMutableList().apply {
                add(Ingredient(amount = "", name = ""))
            }
        )
        _uiState.update { it.copy(ingredients = updatedIngredients) }
    }

    private fun removeIngredientsCard(ingredientsGroupIndex: Int) {
        val updatedIngredients = _uiState.value.ingredients.toMutableList()
        updatedIngredients.removeAt(ingredientsGroupIndex)
        _uiState.update { it.copy(ingredients = updatedIngredients) }
    }

    private fun addIngredientsGroup() {
        val updatedIngredients = _uiState.value.ingredients.toMutableList()
        updatedIngredients.add(
            IngredientSection(
                title = "",
                ingredientsList = listOf(Ingredient(amount = "", name = ""))
            )
        )
        _uiState.update { it.copy(ingredients = updatedIngredients) }
    }

    private fun changeInstructionsInput(value: String) {
        _uiState.update { it.copy(instructionsInput = value) }
    }

    private fun saveRecipe() = viewModelScope.launch {
        val recipe = FullRecipe(
            id = "",
            title = _uiState.value.titleInput,
            rating = 0,
            imageUrl = "",
            placeholderColor = PlaceholderColor.entries.random(),
            ingredients = _uiState.value.ingredients,
            rawCategories = _uiState.value.categories.mapNotNull {
                if (it?.isSelected == true) it.category else null
            },
            instructions = _uiState.value.instructionsInput
        )

        try {
            val newId = addRecipeUseCase.addRecipe(recipe)
            _uiEffect.emit(AddRecipeEffect.NavigateToRecipeDetails(newId))
        } catch (e: Exception) {
            e.printStackTrace()
            _uiEffect.emit(AddRecipeEffect.ShowSnackBar(stringProvider.getString(uiR.string.core_ui_common_error)))
        }
    }

    private fun showEmptyTitleError() {
        _uiState.update {
            it.copy(
                titleInputErrorState = true,
                titleInputErrorText = stringProvider.getString(R.string.add_recipe_title_error)
            )
        }
    }

    private fun showExitDialog() = viewModelScope.launch {
        _uiEffect.emit(AddRecipeEffect.ShowExitDialog)
    }
}