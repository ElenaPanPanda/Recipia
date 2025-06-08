package com.examplerecipia.feature.groceries.impl.ui

sealed interface GroceriesEvent {
    data class OnNewItemValueChange(val value: String) : GroceriesEvent
    data object OnAddNewItem : GroceriesEvent
    data class OnRemoveListBlock(val index: Int) : GroceriesEvent
    data class OnCheckChanged(
        val shoppingListItemIndex: Int,
        val ingredientIndex: Int
    ) : GroceriesEvent
}