package com.examplerecipia.feature.groceries.impl.domain.usecase

import com.examplerecipia.feature.groceries.impl.domain.model.ShoppingListItem

interface UpdateListBlockUseCase {
    suspend fun updateListBlock(index: Int, updatedItem: ShoppingListItem)
}