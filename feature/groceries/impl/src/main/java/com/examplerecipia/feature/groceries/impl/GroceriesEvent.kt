package com.examplerecipia.feature.groceries.impl

sealed interface GroceriesEvent {
    data class OnNewItemValueChange(val value: String) : GroceriesEvent
    data object OnAddNewItem : GroceriesEvent
    data class OnRemoveListBlock(val index: Int) : GroceriesEvent
    data class OnCheckChanged(
        val groceriesListItemIndex: Int,
        val ingredientIndex: Int
    ) : GroceriesEvent

    data object OnClearChecked : GroceriesEvent
    data object OnClearAll : GroceriesEvent
}