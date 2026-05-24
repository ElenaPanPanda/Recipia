package com.example.recipia.core.domain.groceries.usecase

import com.example.datastore.ShoppingListRepository
import javax.inject.Inject

interface ClearGroceriesUseCase {
    suspend operator fun invoke()
}

internal class ClearGroceriesUseCaseImpl @Inject constructor(
    private val repository: ShoppingListRepository
) : ClearGroceriesUseCase {
    override suspend operator fun invoke() = repository.clear()
}