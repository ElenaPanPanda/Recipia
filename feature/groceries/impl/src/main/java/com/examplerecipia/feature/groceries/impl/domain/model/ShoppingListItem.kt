package com.examplerecipia.feature.groceries.impl.domain.model

import com.example.datastore.ShoppingListIngredientDatastoreModel
import com.example.datastore.ShoppingListItemDatastoreModel

data class ShoppingListItem(
    val title: String,
    val ingredientsList: List<ShoppingListIngredient>
)

data class ShoppingListIngredient(
    val amount: String,
    val name: String,
    val isCrossedOut: Boolean,
)

fun ShoppingListItemDatastoreModel.toDomain(): ShoppingListItem =
    ShoppingListItem(
        title = title,
        ingredientsList = ingredientsList.map { it.toDomain() }
    )

private fun ShoppingListIngredientDatastoreModel.toDomain(): ShoppingListIngredient =
    ShoppingListIngredient(
        amount = amount,
        name = name,
        isCrossedOut = isCrossedOut
    )

fun ShoppingListItem.toDatastoreModel(): ShoppingListItemDatastoreModel =
    ShoppingListItemDatastoreModel(
        title = title,
        ingredientsList = ingredientsList.map { it.toDatastoreModel() }
    )

private fun ShoppingListIngredient.toDatastoreModel(): ShoppingListIngredientDatastoreModel =
    ShoppingListIngredientDatastoreModel(
        amount = amount,
        name = name,
        isCrossedOut = isCrossedOut
    )