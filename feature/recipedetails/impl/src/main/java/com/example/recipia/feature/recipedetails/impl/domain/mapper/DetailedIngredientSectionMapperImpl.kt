package com.example.recipia.feature.recipedetails.impl.domain.mapper

import com.example.datastore.ShoppingListItemDatastoreModel
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredientSection
import javax.inject.Inject

internal class DetailedIngredientSectionMapperImpl @Inject constructor(
    private val detailedIngredientMapper: DetailedIngredientMapper
): DetailedIngredientSectionMapper {
    override fun convertToDomain(value: ShoppingListItemDatastoreModel): DetailedIngredientSection {
        with(value) {
            return DetailedIngredientSection(
                title = title,
                ingredientsList = value.ingredientsList.map { detailedIngredientMapper.convertToDomain(it) }
            )
        }
    }

    override fun convertToDatastoreModel(value: DetailedIngredientSection): ShoppingListItemDatastoreModel {
        with(value) {
            return ShoppingListItemDatastoreModel(
                title = title ?: "",
                ingredientsList = value.ingredientsList.map {
                    detailedIngredientMapper.convertToDatastoreModel(it)
                }
            )
        }
    }
}