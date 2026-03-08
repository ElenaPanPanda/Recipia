package com.example.recipia.core.domain.groceries

import com.example.datastore.ShoppingListIngredientDatastoreModel
import com.example.datastore.ShoppingListItemDatastoreModel
import com.example.recipia.core.common.model.GroceriesIngredient
import com.example.recipia.core.common.model.GroceriesItem

internal object GroceriesDomainTestHelper {
    private const val TITLE = "title"
    private const val INGREDIENT_AMOUNT = "200g"
    private const val INGREDIENT_NAME = "Flour"
    private const val IS_CROSSED_OUT = false

    fun createShoppingListIngredientDatastoreModel(
        amount: String = INGREDIENT_AMOUNT,
        name: String = INGREDIENT_NAME,
        isCrossedOut: Boolean = IS_CROSSED_OUT
    ) = ShoppingListIngredientDatastoreModel(
        amount = amount,
        name = name,
        isCrossedOut = isCrossedOut
    )

    fun createGroceriesIngredient(
        amount: String = INGREDIENT_AMOUNT,
        name: String = INGREDIENT_NAME,
        isCrossedOut: Boolean = IS_CROSSED_OUT
    ) = GroceriesIngredient(
        amount = amount,
        name = name,
        isCrossedOut = isCrossedOut
    )

    fun createShoppingListItemDatastoreModel(
        title: String = TITLE,
        ingredientsList: List<ShoppingListIngredientDatastoreModel> = listOf(createShoppingListIngredientDatastoreModel())
    ) = ShoppingListItemDatastoreModel(
        title = title,
        ingredientsList = ingredientsList
    )

    fun createGroceriesItem(
        title: String = TITLE,
        ingredientsList: List<GroceriesIngredient> = listOf(createGroceriesIngredient())
    ) = GroceriesItem(
        title = title,
        ingredientsList = ingredientsList
    )
}