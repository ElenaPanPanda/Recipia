package com.examplerecipia.feature.groceries.impl.domain.mapper

import com.example.datastore.ShoppingListIngredientDatastoreModel
import com.examplerecipia.feature.groceries.impl.domain.model.ShoppingListIngredient

internal interface ShoppingListIngredientMapper {
    fun convertToDomain(value: ShoppingListIngredientDatastoreModel): ShoppingListIngredient

    fun convertToDatastoreModel(value: ShoppingListIngredient): ShoppingListIngredientDatastoreModel
}