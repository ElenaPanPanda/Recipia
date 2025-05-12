package com.example.recipia.feature.recipedetails.impl.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipia.core.common.string_res_provider.StringResProvider
import com.example.recipia.feature.recipedetails.impl.domain.usecase.GetRecipeUseCase
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
    savedStateHandle: SavedStateHandle,
    private val stringProvider: StringResProvider,
    private val getRecipeUseCase: GetRecipeUseCase,
) : ViewModel() {
    private val recipeId: String = savedStateHandle["recipeId"]
        ?: throw IllegalStateException("recipeId is null")

    private val _uiState = MutableStateFlow<RecipeDetailsState>(RecipeDetailsState.Loading)
    val uiState: StateFlow<RecipeDetailsState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<RecipeDetailsEffect>()
    val uiEffect: SharedFlow<RecipeDetailsEffect> = _uiEffect.asSharedFlow()

    fun obtainEvent(event: RecipeDetailsEvent) {
        when (event) {
            is RecipeDetailsEvent.OnEditClicked -> onEditClick(event.recipeId)
            is RecipeDetailsEvent.OnSaveClicked -> onSaveClick(event.recipeId)
            is RecipeDetailsEvent.OnCalendarClicked -> onCalendarClick(event.recipeId)
            is RecipeDetailsEvent.OnShareClicked -> onShareClick(event.recipeId)
            is RecipeDetailsEvent.OnDeleteClicked -> onDeleteClick(event.recipeId)
        }
    }

    init {
        loadRecipe(recipeId)
    }

    private fun loadRecipe(recipeId: String) = viewModelScope.launch {
        try {
            val recipe = getRecipeUseCase.getRecipe(recipeId)
            _uiState.update { RecipeDetailsState.Success(recipe = recipe) }
        } catch (e: Exception) {
            e.printStackTrace()
            _uiState.update { RecipeDetailsState.Error(message = "Recipe not found") } // TODO: localize
        }
    }

    private fun onEditClick(recipeId: String) {} // TODO: Navigate to edit screen

    private fun onSaveClick(recipeId: String) {} // TODO: Save recipe

    private fun onCalendarClick(recipeId: String) {} // TODO: Navigate to calendar screen

    private fun onShareClick(recipeId: String) {} // TODO: Share recipe

    private fun onDeleteClick(recipeId: String) {} // TODO: Delete recipe
}