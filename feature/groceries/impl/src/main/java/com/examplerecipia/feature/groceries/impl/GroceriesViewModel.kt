package com.examplerecipia.feature.groceries.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipia.core.common.model.GroceriesIngredient
import com.example.recipia.core.common.string_res_provider.StringResProvider
import com.example.recipia.core.domain.groceries.usecase.AddGroceriesBlockUseCase
import com.example.recipia.core.domain.groceries.usecase.ClearGroceriesUseCase
import com.example.recipia.core.domain.groceries.usecase.GetGroceriesListUseCase
import com.example.recipia.core.domain.groceries.usecase.RemoveCheckedGroceriesItemsUseCase
import com.example.recipia.core.domain.groceries.usecase.RemoveGroceriesListBlockUseCase
import com.example.recipia.core.domain.groceries.usecase.UpdateGroceriesListBlockUseCase
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
class GroceriesViewModel @Inject constructor(
    private val stringProvider: StringResProvider,
    private val getGroceriesListUseCase: GetGroceriesListUseCase,
    private val addGroceriesBlockUseCase: AddGroceriesBlockUseCase,
    private val updateGroceriesListBlockUseCase: UpdateGroceriesListBlockUseCase,
    private val removeGroceriesListBlockUseCase: RemoveGroceriesListBlockUseCase,
    private val clearGroceriesUseCase: ClearGroceriesUseCase,
    private val removeCheckedGroceriesItemsUseCase: RemoveCheckedGroceriesItemsUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<GroceriesState>(GroceriesState.Loading)
    val uiState: StateFlow<GroceriesState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<GroceriesEffect>()
    val uiEffect: SharedFlow<GroceriesEffect> = _uiEffect.asSharedFlow()

    fun obtainEvent(event: GroceriesEvent) {
        when (event) {
            is GroceriesEvent.OnNewItemValueChange -> changeNewItemValue(event.value)
            is GroceriesEvent.OnAddNewItem -> addNewItem()
            is GroceriesEvent.OnRemoveListBlock -> removeListBlock(event.index)
            is GroceriesEvent.OnCheckChanged -> updateCheckedStatus(
                event.groceriesListItemIndex,
                event.ingredientIndex
            )

            is GroceriesEvent.OnClearChecked -> clearChecked()
            is GroceriesEvent.OnClearAll -> clearAll()
        }
    }

    init {
        observeGroceriesList()
    }

    private fun observeGroceriesList() {
        viewModelScope.launch {
            getGroceriesListUseCase().collect { list ->
                _uiState.value = GroceriesState.Success(groceriesList = list)
            }
        }
    }

    private fun changeNewItemValue(value: String) {
        val currentState = uiState.value
        if (currentState !is GroceriesState.Success) return

        _uiState.update { currentState.copy(newItemValue = value) }
    }

    private fun addNewItem() {
        val currentState = uiState.value
        if (currentState !is GroceriesState.Success) return

        viewModelScope.launch {
            val manualAddingTitle = stringProvider.getString(R.string.groceries_other_items)
            val newValue = currentState.newItemValue.trim()
            if (!validateNewItem(newValue)) return@launch

            val manualSectionExists =
                currentState.groceriesList.isNotEmpty() && currentState.groceriesList.first().title == manualAddingTitle

            if (manualSectionExists) {
                val updatedItem = currentState.groceriesList.first().copy(
                    ingredientsList = currentState.groceriesList.first().ingredientsList +
                            GroceriesIngredient(
                                amount = "",
                                name = currentState.newItemValue,
                                isCrossedOut = false
                            )
                )

                updateGroceriesListBlockUseCase(0, updatedItem)
            } else {
                addGroceriesBlockUseCase(
                    newTitle = manualAddingTitle,
                    newValue = newValue
                )
            }
        }
    }

    private fun validateNewItem(value: String): Boolean {
        return value.isNotBlank()
    }

    private fun removeListBlock(index: Int) {
        viewModelScope.launch {
            val currentState = uiState.value

            if (currentState is GroceriesState.Success) {
                removeGroceriesListBlockUseCase(index)
            }
        }
    }

    private fun updateCheckedStatus(shoppingListItemIndex: Int, ingredientIndex: Int) {
        val currentState = uiState.value
        if (currentState !is GroceriesState.Success) return

        viewModelScope.launch {
            val newItem = currentState.groceriesList[shoppingListItemIndex].copy(
                ingredientsList = currentState.groceriesList[shoppingListItemIndex].ingredientsList.mapIndexed { index, ingredient ->
                    if (index == ingredientIndex) {
                        ingredient.copy(isCrossedOut = !ingredient.isCrossedOut)
                    } else {
                        ingredient
                    }
                }
            )
            updateGroceriesListBlockUseCase(shoppingListItemIndex, newItem)
        }
    }

    private fun clearChecked() {
        val currentState = uiState.value
        if (currentState is GroceriesState.Success) {
            viewModelScope.launch {
                removeCheckedGroceriesItemsUseCase(currentState.groceriesList)
            }
        }
    }

    private fun clearAll() {
        viewModelScope.launch {
            clearGroceriesUseCase()
        }
    }
}