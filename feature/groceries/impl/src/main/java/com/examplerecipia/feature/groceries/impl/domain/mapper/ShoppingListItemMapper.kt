package com.examplerecipia.feature.groceries.impl.domain.mapper

import com.example.datastore.ShoppingListItemDatastoreModel
import com.examplerecipia.feature.groceries.impl.domain.model.ShoppingListItem

internal interface ShoppingListItemMapper {
    fun convertToDomain(value: ShoppingListItemDatastoreModel): ShoppingListItem

    fun convertToDatastoreModel(value: ShoppingListItem): ShoppingListItemDatastoreModel
}