package com.examplerecipia.feature.groceries.impl.ui

sealed class GroceriesEffect {
    data class ShowSnackBar(val message: String) : GroceriesEffect()
}