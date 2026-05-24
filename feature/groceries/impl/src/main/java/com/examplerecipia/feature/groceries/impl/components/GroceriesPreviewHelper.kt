package com.examplerecipia.feature.groceries.impl.components

import com.example.recipia.core.common.model.GroceriesIngredient
import com.example.recipia.core.common.model.GroceriesItem

internal class GroceriesPreviewHelper {
    fun createGroceriesItemList() = listOf(
        createGroceriesItemWithOneIngredient(),
        createGroceriesItemWithOneCrossedOutItem(),
        createGroceriesItemWithoutCrossedOutItems(),
    )

    private fun createGroceriesItemWithOneIngredient() = GroceriesItem(
        title = "Golden Chickpea & Spinach Curry",
        ingredientsList = listOf(
            createGroceriesIngredient("1 can (13.4)", "Coconut milk (full-fat)", false),
        )
    )

    fun createGroceriesItemWithOneCrossedOutItem() = GroceriesItem(
        title = "Golden Chickpea & Spinach Curry & Spiced Pumpkin & Lentil Stew",
        ingredientsList = listOf(
            createGroceriesIngredient("1 can (13.4)", "Coconut milk (full-fat)", true),
            createGroceriesIngredient("2 tbsp", "Curry powder", false),
        )
    )

    private fun createGroceriesItemWithoutCrossedOutItems() = GroceriesItem(
        title = "Golden Chickpea & Spinach Curry & Spiced Pumpkin & Lentil Stew",
        ingredientsList = listOf(
            createGroceriesIngredient("1 can (13.4)", "Coconut milk (full-fat)", false),
            createGroceriesIngredient("2 tbsp", "Curry  powder", false),
            createGroceriesIngredient("1 can (13.4)", "Coconut milk (full-fat)", false),
        )
    )

    fun createGroceriesIngredient(
        amount: String = "1 can (13.4)",
        name: String = "Coconut milk (full-fat)",
        isCrossedOut: Boolean = false
    ) =
        GroceriesIngredient(
            amount = amount,
            name = name,
            isCrossedOut = isCrossedOut,
        )
}