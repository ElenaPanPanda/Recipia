package com.example.recipia.feature.recipedetails.impl.domain.mapper

import com.example.datastore.ShoppingListIngredientDatastoreModel
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredient
import javax.inject.Inject

internal class DetailedIngredientMapperImpl @Inject constructor() : DetailedIngredientMapper {
    override fun convertToDatastoreModel(value: DetailedIngredient): ShoppingListIngredientDatastoreModel {
        return ShoppingListIngredientDatastoreModel(
            amount = value.amount,
            name = value.ingredient,
            isCrossedOut = false
        )
    }

    override fun convertToDomain(value: ShoppingListIngredientDatastoreModel): DetailedIngredient {
        return DetailedIngredient(
            amount = value.amount,
            ingredient = value.name,
            addedToList = true
        )
    }
}