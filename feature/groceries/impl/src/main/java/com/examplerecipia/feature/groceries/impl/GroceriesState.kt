package com.examplerecipia.feature.groceries.impl

import com.example.recipia.core.common.model.GroceriesItem

sealed interface GroceriesState {
    data object Loading : GroceriesState
    data class Error(val message: String) : GroceriesState
    data class Success(
        val groceriesList: List<GroceriesItem> = emptyList(),
        val newItemValue: String = "",
        val isClearCheckedEnabled: Boolean = false,
        val isClearAllEnabled: Boolean = false,
    ) : GroceriesState
}