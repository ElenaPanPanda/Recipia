package com.examplerecipia.feature.groceries.impl.domain.model

data class ShoppingListIngredient(
    val amount: String,
    val name: String,
    val isCrossedOut: Boolean,
)