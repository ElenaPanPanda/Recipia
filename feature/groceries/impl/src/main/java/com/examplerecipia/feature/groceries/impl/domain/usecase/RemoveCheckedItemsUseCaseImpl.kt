package com.examplerecipia.feature.groceries.impl.domain.usecase

import com.example.datastore.ShoppingListRepository
import com.examplerecipia.feature.groceries.impl.domain.mapper.ShoppingListItemMapper
import com.examplerecipia.feature.groceries.impl.domain.model.ShoppingListItem
import javax.inject.Inject

internal class RemoveCheckedItemsUseCaseImpl @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository,
    private val mapper: ShoppingListItemMapper,
) : RemoveCheckedItemsUseCase {
    override suspend fun remove(shoppingList: List<ShoppingListItem>) {
        shoppingList.forEachIndexed { index, shoppingListItem ->
            val remainingIngredients =
                shoppingListItem.ingredientsList.filter { ingredient -> !ingredient.isCrossedOut }
            val updated = shoppingListItem.copy(ingredientsList = remainingIngredients)
            shoppingListRepository.updateItem(
                index = index,
                item = mapper.convertToDatastoreModel(updated)
            )
        }
    }
}