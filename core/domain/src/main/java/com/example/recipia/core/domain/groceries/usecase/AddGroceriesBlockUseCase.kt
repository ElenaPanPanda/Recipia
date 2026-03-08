package com.example.recipia.core.domain.groceries.usecase

import com.example.datastore.ShoppingListRepository
import com.example.recipia.core.common.model.GroceriesIngredient
import com.example.recipia.core.common.model.GroceriesItem
import com.example.recipia.core.domain.groceries.mappers.GroceriesItemMapper
import javax.inject.Inject

interface AddGroceriesBlockUseCase {
    suspend operator fun invoke(newTitle: String, newValue: String)
}

internal class AddGroceriesBlockUseCaseImpl @Inject constructor(
    private val repository: ShoppingListRepository,
    private val mapper: GroceriesItemMapper,
) : AddGroceriesBlockUseCase {
    override suspend operator fun invoke(newTitle: String, newValue: String) {
        val newGroceriesItem = createGroceriesItem(newTitle, newValue)
        repository.addItem(mapper.convertToDto(newGroceriesItem))
    }

    private fun createGroceriesItem(title: String, ingredientName: String): GroceriesItem {
        return GroceriesItem(
            title = title,
            ingredientsList = listOf(
                GroceriesIngredient(
                    amount = "",
                    name = ingredientName,
                    isCrossedOut = false
                )
            )
        )
    }
}