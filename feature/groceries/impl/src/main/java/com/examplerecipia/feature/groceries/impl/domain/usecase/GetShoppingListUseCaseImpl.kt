package com.examplerecipia.feature.groceries.impl.domain.usecase

import com.example.datastore.ShoppingListRepository
import com.examplerecipia.feature.groceries.impl.domain.mapper.ShoppingListItemMapper
import com.examplerecipia.feature.groceries.impl.domain.model.ShoppingListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class GetShoppingListUseCaseImpl @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository,
    private val mapper: ShoppingListItemMapper,
) : GetShoppingListUseCase {
    override suspend fun getShoppingList(): Flow<List<ShoppingListItem>> {
        return shoppingListRepository.shoppingListFlow.map { list ->
            list.map { mapper.convertToDomain(it) }
        }
    }
}