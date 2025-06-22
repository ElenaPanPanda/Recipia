package com.example.recipia.feature.recipedetails.impl.domain.mapper

import com.example.datastore.ShoppingListIngredientDatastoreModel
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredient

internal interface DetailedIngredientMapper {
    fun convertToDatastoreModel(value: DetailedIngredient): ShoppingListIngredientDatastoreModel

    fun convertToDomain(value: ShoppingListIngredientDatastoreModel): DetailedIngredient
}