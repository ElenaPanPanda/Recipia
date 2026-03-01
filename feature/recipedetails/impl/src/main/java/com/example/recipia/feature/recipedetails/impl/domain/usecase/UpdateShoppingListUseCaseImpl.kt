package com.example.recipia.feature.recipedetails.impl.domain.usecase

import com.example.datastore.ShoppingListRepository
import com.example.recipia.feature.recipedetails.impl.domain.mapper.DetailedIngredientSectionMapper
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredientSection
import javax.inject.Inject

internal class UpdateShoppingListUseCaseImpl @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository,
    private val mapper: DetailedIngredientSectionMapper
) : UpdateShoppingListUseCase {
    override suspend fun update(index: Int, updatedItem: DetailedIngredientSection) {
        shoppingListRepository.updateItem(index, mapper.convertToDatastoreModel(updatedItem))
    }
}