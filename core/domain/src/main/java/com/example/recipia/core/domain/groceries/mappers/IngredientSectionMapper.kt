package com.example.recipia.core.domain.groceries.mappers

import com.example.datastore.ShoppingListItemDatastoreModel
import com.example.recipia.core.common.model.IngredientSection
import javax.inject.Inject

interface IngredientSectionMapper {
    fun convertToDomain(value: ShoppingListItemDatastoreModel): IngredientSection

    fun convertToDto(value: IngredientSection): ShoppingListItemDatastoreModel
}

internal class IngredientSectionMapperImpl @Inject constructor(
    private val ingredientMapper: IngredientMapper
) : IngredientSectionMapper {
    override fun convertToDomain(value: ShoppingListItemDatastoreModel): IngredientSection {
        with(value) {
            return IngredientSection(
                title = title,
                ingredientsList = value.ingredientsList.map { ingredientMapper.convertToDomain(it) }
            )
        }
    }

    override fun convertToDto(value: IngredientSection): ShoppingListItemDatastoreModel {
        with(value) {
            return ShoppingListItemDatastoreModel(
                title = title ?: "",
                ingredientsList = value.ingredientsList.map {
                    ingredientMapper.convertToDto(it)
                }
            )
        }
    }
}