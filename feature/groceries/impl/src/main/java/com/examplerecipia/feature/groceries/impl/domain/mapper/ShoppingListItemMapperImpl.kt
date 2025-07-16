package com.examplerecipia.feature.groceries.impl.domain.mapper

import com.example.datastore.ShoppingListItemDatastoreModel
import com.examplerecipia.feature.groceries.impl.domain.model.ShoppingListItem
import javax.inject.Inject

internal class ShoppingListItemMapperImpl @Inject constructor(
    private val ingredientsListMapper: ShoppingListIngredientMapper
) : ShoppingListItemMapper {
    override fun convertToDomain(value: ShoppingListItemDatastoreModel): ShoppingListItem {
        with(value) {
            return ShoppingListItem(
                title = title,
                ingredientsList = value.ingredientsList.map { ingredientsListMapper.convertToDomain(it) }
            )
        }
    }

    override fun convertToDatastoreModel(value: ShoppingListItem): ShoppingListItemDatastoreModel {
        with(value) {
            return ShoppingListItemDatastoreModel(
                title = title,
                ingredientsList = value.ingredientsList.map {
                    ingredientsListMapper.convertToDatastoreModel(it)
                }
            )
        }
    }
}