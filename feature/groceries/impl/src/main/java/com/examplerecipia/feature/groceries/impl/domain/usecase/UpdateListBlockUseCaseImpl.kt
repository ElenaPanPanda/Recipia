package com.examplerecipia.feature.groceries.impl.domain.usecase

import com.example.datastore.ShoppingListRepository
import com.examplerecipia.feature.groceries.impl.domain.model.ShoppingListItem
import com.examplerecipia.feature.groceries.impl.domain.model.toDatastoreModel
import javax.inject.Inject

internal class UpdateListBlockUseCaseImpl @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository
) : UpdateListBlockUseCase {
    override suspend fun updateListBlock(index: Int, updatedItem: ShoppingListItem) {
        shoppingListRepository.updateItem(index, updatedItem.toDatastoreModel())
    }
}