package com.examplerecipia.feature.groceries.impl.domain.usecase

import com.example.datastore.ShoppingListRepository
import javax.inject.Inject

internal class RemoveListBlockUseCaseImpl @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository
) : RemoveListBlockUseCase {
    override suspend fun removeListBlock(index: Int) {
        shoppingListRepository.removeItem(index)
    }
}