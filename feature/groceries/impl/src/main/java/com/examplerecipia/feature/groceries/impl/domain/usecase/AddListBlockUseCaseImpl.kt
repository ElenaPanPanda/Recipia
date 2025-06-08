package com.examplerecipia.feature.groceries.impl.domain.usecase

import com.example.datastore.ShoppingListRepository
import com.examplerecipia.feature.groceries.impl.domain.model.ShoppingListIngredient
import com.examplerecipia.feature.groceries.impl.domain.model.toDatastoreModel
import javax.inject.Inject

internal class AddListBlockUseCaseImpl @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository
) : AddListBlockUseCase {
    override suspend fun addListBlock(newTitle: String, newValue: String) {
        val newItem = com.examplerecipia.feature.groceries.impl.domain.model.ShoppingListItem(
            title = newTitle,
            ingredientsList = listOf(
                ShoppingListIngredient(
                    amount = "",
                    name = newValue,
                    isCrossedOut = false
                )
            )
        )
        shoppingListRepository.addItem(newItem.toDatastoreModel())
    }
}