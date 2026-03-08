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
        for (index in groceriesList.indices.reversed()) {
            val groceriesItem = groceriesList[index]
            val remainingIngredients = groceriesItem.ingredientsList.filter { !it.isCrossedOut }

            if (remainingIngredients.isEmpty()) {
                repository.removeItem(index)
            } else if (remainingIngredients.size < groceriesItem.ingredientsList.size) {
                val updatedItem = groceriesItem.copy(ingredientsList = remainingIngredients)
                repository.updateItem(index, mapper.convertToDto(updatedItem))
            }
        }
    }
}