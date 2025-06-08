package com.examplerecipia.feature.groceries.impl.domain.usecase

import com.example.datastore.ShoppingListRepository
import com.examplerecipia.feature.groceries.impl.domain.model.ShoppingListItem
import com.examplerecipia.feature.groceries.impl.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class GetShoppingListUseCaseImpl @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository
) : GetShoppingListUseCase {
    override suspend fun getShoppingList(): Flow<List<ShoppingListItem>> {
        return shoppingListRepository.shoppingListFlow.map { list ->
            list.map { it.toDomain() }
        }
    }
}