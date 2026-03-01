package com.examplerecipia.feature.groceries.impl.domain.usecase

import com.examplerecipia.feature.groceries.impl.domain.model.ShoppingListItem

interface RemoveCheckedItemsUseCase {
    suspend fun remove(shoppingList: List<ShoppingListItem>)
}