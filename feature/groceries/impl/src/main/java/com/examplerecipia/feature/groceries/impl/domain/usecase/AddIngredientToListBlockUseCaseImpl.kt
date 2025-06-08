package com.examplerecipia.feature.groceries.impl.domain.usecase

import com.example.datastore.ShoppingListRepository
import com.examplerecipia.feature.groceries.impl.domain.model.ShoppingListIngredient
import com.examplerecipia.feature.groceries.impl.domain.model.toDatastoreModel
import javax.inject.Inject

internal class AddIngredientToListBlockUseCaseImpl @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository
) : AddIngredientToListBlockUseCase {
    override suspend fun updateListBlock(index: Int, newValue: String) {
        val newIngredient = ShoppingListIngredient(
            amount = "",
            name = newValue,
            isCrossedOut = false
        )
        shoppingListRepository.addIngredientToItem(0, newIngredient.toDatastoreModel())
    }
}