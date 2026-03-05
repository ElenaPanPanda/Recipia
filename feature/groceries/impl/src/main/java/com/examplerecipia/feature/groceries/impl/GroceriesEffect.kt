package com.examplerecipia.feature.groceries.impl

sealed class GroceriesEffect {
    data class ShowSnackBar(val message: String) : GroceriesEffect()
}