package com.examplerecipia.feature.groceries.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datastore.ShoppingListRepository
import com.examplerecipia.feature.groceries.impl.domain.model.ShoppingListIngredient
import com.examplerecipia.feature.groceries.impl.domain.model.ShoppingListItem
import com.examplerecipia.feature.groceries.impl.domain.model.toDatastoreModel
import com.examplerecipia.feature.groceries.impl.domain.model.toDomain
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
    private val shoppingListRepository: ShoppingListRepository,
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
            shoppingListRepository.shoppingListFlow.collect { list ->
                val domainList = list.map { it.toDomain() }

                _uiState.value = GroceriesState.Success(shoppingList = domainList)
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

            if (currentState is GroceriesState.Success) {
                val newItem = ShoppingListItem(
                    title = "Other Items",
                    ingredientsList = listOf(
                        ShoppingListIngredient(
                            amount = "",
                            name = currentState.newItemValue,
                            isCrossedOut = false
                        )
                    )
                )
                shoppingListRepository.addItem(newItem.toDatastoreModel())
            }
        }
    }

    private fun removeListBlock(index: Int) {
        viewModelScope.launch {
            val currentState = uiState.value

            if (currentState is GroceriesState.Success) {
                shoppingListRepository.removeItem(index)
            }
        }
    }
}