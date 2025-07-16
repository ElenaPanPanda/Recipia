package com.examplerecipia.feature.groceries.impl.ui

import com.examplerecipia.feature.groceries.impl.domain.model.ShoppingListItem

sealed interface GroceriesState {
    data object Loading : GroceriesState
    data class Error(val message: String) : GroceriesState
    data class Success(
        val shoppingList: List<ShoppingListItem> = emptyList(),
        val newItemValue: String = "",
    ) : GroceriesState
}