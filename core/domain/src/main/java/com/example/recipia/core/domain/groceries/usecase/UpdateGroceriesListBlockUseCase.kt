package com.example.recipia.core.domain.groceries.usecase

import com.example.datastore.ShoppingListRepository
import com.example.recipia.core.common.model.GroceriesItem
import com.example.recipia.core.domain.groceries.mappers.GroceriesItemMapper

interface UpdateGroceriesListBlockUseCase {
    suspend operator fun invoke(index: Int, updatedItem: GroceriesItem)
}

internal class UpdateGroceriesListBlockUseCaseImpl(
    private val repository: ShoppingListRepository,
    private val mapper: GroceriesItemMapper,
) : UpdateGroceriesListBlockUseCase {
    override suspend operator fun invoke(index: Int, updatedItem: GroceriesItem) {
        repository.updateItem(index, mapper.convertToDto(updatedItem))
    }
}