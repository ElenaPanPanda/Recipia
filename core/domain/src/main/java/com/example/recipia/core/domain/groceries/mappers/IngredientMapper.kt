package com.example.recipia.core.domain.groceries.mappers

import com.example.datastore.ShoppingListIngredientDatastoreModel
import com.example.recipia.core.common.model.Ingredient
import javax.inject.Inject

internal interface IngredientMapper {
    fun convertToDomain(value: ShoppingListIngredientDatastoreModel): Ingredient

    fun convertToDto(value: Ingredient): ShoppingListIngredientDatastoreModel
}

internal class IngredientMapperImpl @Inject constructor() : IngredientMapper {
    override fun convertToDomain(value: ShoppingListIngredientDatastoreModel): Ingredient {
        with(value) { return Ingredient(amount = amount, name = name) }
    }

    override fun convertToDto(value: Ingredient): ShoppingListIngredientDatastoreModel {
        with(value) {
            return ShoppingListIngredientDatastoreModel(
                amount = amount,
                name = name,
                isCrossedOut = false
            )
        }
    }
}