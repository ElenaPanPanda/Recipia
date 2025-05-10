package com.example.recipia.feature.recipedetails.impl.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipia.core.common.string_res_provider.StringResProvider
import com.example.recipia.core.ui.R as uiR
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

    private val _uiState = MutableStateFlow(RecipeDetailsState())
    val uiState: StateFlow<RecipeDetailsState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<RecipeDetailsEffect>()
    val uiEffect: SharedFlow<RecipeDetailsEffect> = _uiEffect.asSharedFlow()

    fun obtainEvent(event: RecipeDetailsEvent) {
        when (event) {
            is RecipeDetailsEvent.OnEditClick -> onEditClick(event.recipeId)
            is RecipeDetailsEvent.OnDeleteClick -> onDeleteClick(event.recipeId)
        }
    }

    init {
        _uiState.update { it.copy(recipeId = recipeId) }
        loadRecipe()
    }

    private fun loadRecipe() = viewModelScope.launch {
        try {
            val recipe = getRecipeUseCase.getRecipe(uiState.value.recipeId)
            _uiState.update { it.copy(isLoading = false, recipe = recipe) }
        } catch (e: Exception) {
            e.printStackTrace()
            _uiEffect.emit(
                RecipeDetailsEffect.ShowSnackBar(
                    stringProvider.getString(uiR.string.core_ui_common_error)
                )
            )
        }
    }

    private fun onEditClick(recipeId: String) {} // TODO: Navigate to edit screen

    private fun onDeleteClick(recipeId: String) {} // TODO: Delete recipe
}