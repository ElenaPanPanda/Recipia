package com.example.recipia.feature.recipedetails.impl.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipia.core.common.string_res_provider.StringResProvider
import com.example.recipia.core.ui.R
import com.example.recipia.feature.recipedetails.impl.domain.usecase.GetRecipeUseCase
import com.example.recipia.feature.recipedetails.impl.ui.managers.RecipeDetailsCollectionsManager
import com.example.recipia.feature.recipedetails.impl.ui.managers.RecipeDetailsGroceriesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val stringProvider: StringResProvider,
    savedStateHandle: SavedStateHandle,
    private val getRecipeUseCase: GetRecipeUseCase,
    private val groceriesManager: RecipeDetailsGroceriesManager,
    private val collectionManager: RecipeDetailsCollectionsManager,
) : ViewModel() {
    private val recipeId: String = savedStateHandle["recipeId"]
        ?: throw IllegalStateException("recipeId is null")

    private val _uiState = MutableStateFlow<RecipeDetailsState>(RecipeDetailsState.Loading)
    val uiState: StateFlow<RecipeDetailsState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<RecipeDetailsEffect>()
    val uiEffect: SharedFlow<RecipeDetailsEffect> = _uiEffect.asSharedFlow()

    private fun updateSuccessState(updater: RecipeDetailsState.Success.() -> RecipeDetailsState.Success) {
        _uiState.update { if (it is RecipeDetailsState.Success) it.updater() else it }
    }

    fun obtainEvent(event: RecipeDetailsEvent) {
        when (event) {
            is RecipeDetailsEvent.OnEditClicked -> onEditClick(event.recipeId)
            is RecipeDetailsEvent.OnSaveIconClicked -> {
                collectionManager.getCollectionsForBottomSheets(
                    scope = viewModelScope,
                    updateState = ::updateSuccessState,
                    emitEffect = { effect -> viewModelScope.launch { _uiEffect.emit(effect) } }
                )
            }

            is RecipeDetailsEvent.OnCalendarClicked -> onCalendarClick(event.recipeId)
            is RecipeDetailsEvent.OnShareClicked -> onShareClick(event.recipeId)
            is RecipeDetailsEvent.OnDeleteClicked -> onDeleteClick(event.recipeId)
            is RecipeDetailsEvent.OnAddAllIngredientsClicked -> {
                groceriesManager.addAllIngredientsToShoppingList(
                    event.recipeName,
                    event.ingredients,
                    viewModelScope
                )
            }

            is RecipeDetailsEvent.OnAddIngredientClicked -> {
                groceriesManager.addIngredientToShoppingList(
                    event.recipeName,
                    event.ingredient,
                    viewModelScope
                )
            }

            is RecipeDetailsEvent.OnCollectionSelectedChange -> {
                collectionManager.selectCollection(event.collectionId, ::updateSuccessState)
            }

            is RecipeDetailsEvent.OnNewCollectionValueChange -> {
                collectionManager.changeNewCollectionValue(event.value, ::updateSuccessState)
            }

            is RecipeDetailsEvent.OnSaveToCollectionClicked -> {
                val currentState = _uiState.value as? RecipeDetailsState.Success
                if (currentState != null) {
                    collectionManager.saveToCollection(
                        recipeId = recipeId,
                        currentState = currentState,
                        emitEffect = { effect -> viewModelScope.launch { _uiEffect.emit(effect) } },
                        scope = viewModelScope,
                    )
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            loadRecipe(recipeId)
        }
    }

    private suspend fun loadRecipe(recipeId: String) {
        try {
            val recipe = getRecipeUseCase.getRecipe(recipeId)
            _uiState.update { RecipeDetailsState.Success(recipe = recipe) }

            // Update checked ingredients.
            groceriesManager.observeCheckedIngredients(recipe, viewModelScope, ::updateSuccessState)
        } catch (e: Exception) {
            e.printStackTrace()
            _uiState.update {
                RecipeDetailsState.Error(
                    message = stringProvider.getString(R.string.core_ui_recipe_not_found_error)
                )
            }
        }
    }

    private fun onEditClick(recipeId: String) {}

    private fun onCalendarClick(recipeId: String) {}

    private fun onShareClick(recipeId: String) {}

    private fun onDeleteClick(recipeId: String) {}
}