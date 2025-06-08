package com.examplerecipia.feature.groceries.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipia.core.common.string_res_provider.StringResProvider
import com.examplerecipia.feature.groceries.impl.R
import com.examplerecipia.feature.groceries.impl.domain.usecase.AddListBlockUseCase
import com.examplerecipia.feature.groceries.impl.domain.usecase.AddIngredientToListBlockUseCase
import com.examplerecipia.feature.groceries.impl.domain.usecase.GetShoppingListUseCase
import com.examplerecipia.feature.groceries.impl.domain.usecase.RemoveListBlockUseCase
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
    private val getShoppingListUseCase: GetShoppingListUseCase,
    private val addListBlockUseCase: AddListBlockUseCase,
    private val addIngredientToListBlock: AddIngredientToListBlockUseCase,
    private val removeListBlockUseCase: RemoveListBlockUseCase,
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
        }
    }

    init {
        observeShoppingList()
    }

    private fun observeShoppingList() {
        viewModelScope.launch {
            getShoppingListUseCase.getShoppingList().collect { list ->
                _uiState.value = GroceriesState.Success(shoppingList = list)
            }
        }
    }

    private fun changeNewItemValue(value: String) {
        _uiState.update { currentState ->
            if (currentState is GroceriesState.Success) {
                currentState.copy(newItemValue = value)
            } else {
                currentState
            }
        }
    }

    private fun addNewItem() {
        viewModelScope.launch {
            val currentState = uiState.value

            if (currentState !is GroceriesState.Success) return@launch

            val manualAddingTitle = stringProvider.getString(R.string.groceries_other_items)
            val newValue = currentState.newItemValue.trim()

            if (currentState.shoppingList.isNotEmpty() && currentState.shoppingList.first().title == manualAddingTitle) {
                addIngredientToListBlock.updateListBlock(
                    index = 0,
                    newValue = newValue
                )
            } else {
                addListBlockUseCase.addListBlock(
                    newTitle = manualAddingTitle,
                    newValue = newValue
                )
            }
        }
    }

    private fun removeListBlock(index: Int) {
        viewModelScope.launch {
            val currentState = uiState.value

            if (currentState is GroceriesState.Success) {
                removeListBlockUseCase.removeListBlock(index)
            }
        }
    }
}