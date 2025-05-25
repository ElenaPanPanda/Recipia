package com.examplerecipia.feature.groceries.impl.ui

import androidx.lifecycle.ViewModel
import com.examplerecipia.feature.groceries.impl.domain.model.ShoppingListIngredient
import com.examplerecipia.feature.groceries.impl.domain.model.ShoppingListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GroceriesViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<GroceriesState>(GroceriesState.Loading)
    val uiState: StateFlow<GroceriesState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<GroceriesEffect>()
    val uiEffect: SharedFlow<GroceriesEffect> = _uiEffect.asSharedFlow()

    fun obtainEvent(event: GroceriesEvent) {
        when (event) {
            is GroceriesEvent.OnNewItemValueChange -> changeNewItemValue(event.value)
            is GroceriesEvent.AddNewItem -> addNewItem()
        }
    }

    init {
        loadShoppingList()
    }

    private fun loadShoppingList() {
        _uiState.update { GroceriesState.Success(shoppingList = temporaryShoppingList) }
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

    private fun addNewItem() {  }
}

val temporaryShoppingList = listOf(
    ShoppingListItem(
        title = "Other Items",
        ingredientsList = listOf(
            ShoppingListIngredient(
                amount = "",
                name = "Apples",
                isCrossedOut = false,
            ),
        )
    ),
    ShoppingListItem(
        title = "Golden Chickpea & Spinach Curry",
        ingredientsList = listOf(
            ShoppingListIngredient(
                amount = "1 can (13.5 oz)",
                name = "Coconut milk (full-fat)",
                isCrossedOut = true,
            ),
            ShoppingListIngredient(
                amount = "2 tbsp",
                name = "Curry powder",
                isCrossedOut = false,
            ),
            ShoppingListIngredient(
                amount = "1 cup",
                name = "Spinach",
                isCrossedOut = false,
            ),
        )
    ),
    ShoppingListItem(
        title = "Spiced Pumpkin & Lentil Stew",
        ingredientsList = listOf(
            ShoppingListIngredient(
                amount = "1 cup",
                name = "Red lentils, rinsed",
                isCrossedOut = false,
            ),
            ShoppingListIngredient(
                amount = "3 cloves",
                name = "Garlic, minced",
                isCrossedOut = false,
            ),
            ShoppingListIngredient(
                amount = "1",
                name = "medium Butternut Pumpkin",
                isCrossedOut = false,
            ),
        )
    ),
    ShoppingListItem(
        title = "Spiced Pumpkin & Lentil Stew",
        ingredientsList = listOf(
            ShoppingListIngredient(
                amount = "1 cup",
                name = "Red lentils, rinsed",
                isCrossedOut = false,
            ),
            ShoppingListIngredient(
                amount = "3 cloves",
                name = "Garlic, minced",
                isCrossedOut = true,
            ),
            ShoppingListIngredient(
                amount = "1",
                name = "medium Butternut Pumpkin",
                isCrossedOut = false,
            ),
        )
    ),
)