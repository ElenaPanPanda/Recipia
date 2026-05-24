package com.example.recipia.core.domain.groceries.mappers

import com.example.datastore.ShoppingListIngredientDatastoreModel
import com.example.recipia.core.common.model.GroceriesIngredient
import javax.inject.Inject

internal interface GroceriesIngredientMapper {
    fun convertToDomain(value: ShoppingListIngredientDatastoreModel): GroceriesIngredient

    fun convertToDto(value: GroceriesIngredient): ShoppingListIngredientDatastoreModel
}

internal class GroceriesIngredientMapperImpl @Inject constructor() : GroceriesIngredientMapper {
    override fun convertToDomain(value: ShoppingListIngredientDatastoreModel): GroceriesIngredient {
        with(value) {
            return GroceriesIngredient(
                amount = amount,
                name = name,
                isCrossedOut = isCrossedOut
            )
        }
    }

    override fun convertToDto(value: GroceriesIngredient): ShoppingListIngredientDatastoreModel {
        with(value) {
            return ShoppingListIngredientDatastoreModel(
                amount = amount,
                name = name,
                isCrossedOut = isCrossedOut
            )
        }
    }
}