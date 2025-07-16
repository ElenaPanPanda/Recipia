package com.examplerecipia.feature.groceries.impl.domain.usecase

import com.examplerecipia.feature.groceries.impl.domain.model.ShoppingListItem
import kotlinx.coroutines.flow.Flow

interface GetShoppingListUseCase {
    suspend fun getShoppingList(): Flow<List<ShoppingListItem>>
}