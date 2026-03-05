package com.example.recipia.core.domain.groceries.usecase

import com.example.datastore.ShoppingListRepository
import com.example.recipia.core.common.model.GroceriesItem
import com.example.recipia.core.domain.groceries.mappers.GroceriesItemMapper
import javax.inject.Inject

interface RemoveCheckedGroceriesItemsUseCase {
    suspend operator fun invoke(groceriesList: List<GroceriesItem>)
}

internal class RemoveCheckedGroceriesItemsUseCaseImpl @Inject constructor(
    private val repository: ShoppingListRepository,
    private val mapper: GroceriesItemMapper,
) : RemoveCheckedGroceriesItemsUseCase {
    override suspend operator fun invoke(groceriesList: List<GroceriesItem>) {
        groceriesList.forEachIndexed { index, groceriesItem ->
            val remainingItem =
                groceriesItem.ingredientsList.filter { ingredient -> !ingredient.isCrossedOut }

            if (remainingItem.isEmpty()) {
                repository.removeItem(index)
            } else {
                val updated = groceriesItem.copy(ingredientsList = remainingItem)
                repository.updateItem(index, mapper.convertToDto(updated))
            }

        }
    }
}