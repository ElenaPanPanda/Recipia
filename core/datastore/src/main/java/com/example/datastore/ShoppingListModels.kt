package com.example.datastore

data class ShoppingListItemDatastoreModel(
    val title: String,
    val ingredientsList: List<ShoppingListIngredientDatastoreModel>
)

data class ShoppingListIngredientDatastoreModel(
    val amount: String,
    val name: String,
    val isCrossedOut: Boolean,
)

fun ShoppingListItem.toDatastoreModel(): ShoppingListItemDatastoreModel =
    ShoppingListItemDatastoreModel(
        title = title,
        ingredientsList = ingredientsListList.map { it.toDatastoreModel() }
    )

private fun ShoppingListIngredient.toDatastoreModel() =
    ShoppingListIngredientDatastoreModel(
        amount = amount,
        name = name,
        isCrossedOut = isCrossedOut
    )

fun ShoppingListItemDatastoreModel.toProto(): ShoppingListItem =
    ShoppingListItem.newBuilder()
        .setTitle(title)
        .addAllIngredientsList(ingredientsList.map { it.toProto() })
        .build()

private fun ShoppingListIngredientDatastoreModel.toProto(): ShoppingListIngredient =
    ShoppingListIngredient.newBuilder()
        .setAmount(amount)
        .setName(name)
        .setIsCrossedOut(isCrossedOut)
        .build()