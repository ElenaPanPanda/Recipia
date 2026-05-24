package com.example.recipia.core.domain.groceries.mappers

import com.example.datastore.ShoppingListItemDatastoreModel
import com.example.recipia.core.common.model.GroceriesItem
import javax.inject.Inject

internal interface GroceriesItemMapper {
    fun convertToDomain(value: ShoppingListItemDatastoreModel): GroceriesItem

    fun convertToDto(value: GroceriesItem): ShoppingListItemDatastoreModel
}

internal class GroceriesItemMapperImpl @Inject constructor(
    private val groceriesIngredientsMapper: GroceriesIngredientMapper
) : GroceriesItemMapper {
    override fun convertToDomain(value: ShoppingListItemDatastoreModel): GroceriesItem {
        with(value) {
            return GroceriesItem(
                title = title,
                ingredientsList = value.ingredientsList.map {
                    groceriesIngredientsMapper.convertToDomain(it)
                }
            )
        }
    }

    override fun convertToDto(value: GroceriesItem): ShoppingListItemDatastoreModel {
        with(value) {
            return ShoppingListItemDatastoreModel(
                title = title,
                ingredientsList = value.ingredientsList.map {
                    groceriesIngredientsMapper.convertToDto(it)
                }
            )
        }
    }
}