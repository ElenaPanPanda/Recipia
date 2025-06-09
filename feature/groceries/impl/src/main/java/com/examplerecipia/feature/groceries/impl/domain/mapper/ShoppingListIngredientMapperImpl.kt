package com.examplerecipia.feature.groceries.impl.domain.mapper

import com.example.datastore.ShoppingListIngredientDatastoreModel
import com.examplerecipia.feature.groceries.impl.domain.model.ShoppingListIngredient
import javax.inject.Inject

internal class ShoppingListIngredientMapperImpl @Inject constructor() :
    ShoppingListIngredientMapper {
    override fun convertToDomain(value: ShoppingListIngredientDatastoreModel): ShoppingListIngredient {
        with(value) {
            return ShoppingListIngredient(
                amount = amount,
                name = name,
                isCrossedOut = isCrossedOut
            )
        }
    }

    override fun convertToDatastoreModel(value: ShoppingListIngredient): ShoppingListIngredientDatastoreModel {
        with(value) {
            return ShoppingListIngredientDatastoreModel(
                amount = amount,
                name = name,
                isCrossedOut = isCrossedOut
            )
        }
    }
}