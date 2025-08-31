package com.examplerecipia.feature.groceries.impl.domain.usecase

import com.example.datastore.ShoppingListRepository
import javax.inject.Inject

internal class ClearShoppingListUseCaseImpl @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository,
) : ClearShoppingListUseCase {
    override suspend fun clear() {
        shoppingListRepository.clear()
    }
}