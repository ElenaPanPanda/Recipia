package com.example.recipia.feature.recipedetails.impl.domain.mapper

import com.example.datastore.ShoppingListItemDatastoreModel
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredientSection

internal interface DetailedIngredientSectionMapper {
    fun convertToDomain(value: ShoppingListItemDatastoreModel): DetailedIngredientSection

    fun convertToDatastoreModel(value: DetailedIngredientSection): ShoppingListItemDatastoreModel
}