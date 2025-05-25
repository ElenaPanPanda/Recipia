package com.examplerecipia.feature.groceries.impl.ui

sealed interface GroceriesEvent {
    data class OnNewItemValueChange(val value: String) : GroceriesEvent
    data object AddNewItem : GroceriesEvent
}