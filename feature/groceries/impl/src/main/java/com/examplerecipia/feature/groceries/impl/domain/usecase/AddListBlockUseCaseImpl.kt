package com.examplerecipia.feature.groceries.impl.domain.usecase

import com.example.datastore.ShoppingListRepository
import com.examplerecipia.feature.groceries.impl.domain.mapper.ShoppingListItemMapper
import com.examplerecipia.feature.groceries.impl.domain.model.ShoppingListIngredient
import com.examplerecipia.feature.groceries.impl.domain.model.ShoppingListItem
import javax.inject.Inject

internal class AddListBlockUseCaseImpl @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository,
    private val mapper: ShoppingListItemMapper,
) : AddListBlockUseCase {
    override suspend fun addListBlock(newTitle: String, newValue: String) {
        val newItem = ShoppingListItem(
            title = newTitle,
            ingredientsList = listOf(
                ShoppingListIngredient(
                    amount = "",
                    name = newValue,
                    isCrossedOut = false
                )
            )
        )
        shoppingListRepository.addItem(mapper.convertToDatastoreModel(newItem))
    }
}