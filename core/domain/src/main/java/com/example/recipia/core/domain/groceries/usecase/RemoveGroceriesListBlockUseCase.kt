package com.example.recipia.core.domain.groceries.usecase

import com.example.datastore.ShoppingListRepository
import javax.inject.Inject

interface RemoveGroceriesListBlockUseCase {
    suspend operator fun invoke(index: Int)
}

internal class RemoveGroceriesListBlockUseCaseImpl @Inject constructor(
    private val repository: ShoppingListRepository
) : RemoveGroceriesListBlockUseCase {
    override suspend operator fun invoke(index: Int) = repository.removeItem(index)
}