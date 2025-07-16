package com.examplerecipia.feature.groceries.impl.domain.usecase

import com.example.datastore.ShoppingListRepository
import com.examplerecipia.feature.groceries.impl.domain.mapper.ShoppingListItemMapper
import com.examplerecipia.feature.groceries.impl.domain.model.ShoppingListItem
import javax.inject.Inject

internal class UpdateListBlockUseCaseImpl @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository,
    private val mapper: ShoppingListItemMapper
) : UpdateListBlockUseCase {
    override suspend fun updateListBlock(index: Int, updatedItem: ShoppingListItem) {
        shoppingListRepository.updateItem(index, mapper.convertToDatastoreModel(updatedItem))
    }
}