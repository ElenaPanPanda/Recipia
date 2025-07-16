package com.examplerecipia.feature.groceries.impl.domain.model

data class ShoppingListItem(
    val title: String,
    val ingredientsList: List<ShoppingListIngredient>
)