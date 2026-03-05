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
        val newItem = GroceriesItem(
            title = newTitle,
            ingredientsList = listOf(
                GroceriesIngredient(
                    amount = "",
                    name = newValue,
                    isCrossedOut = false
                )
            )
        )
        repository.addItem(mapper.convertToDto(newItem))
    }
}