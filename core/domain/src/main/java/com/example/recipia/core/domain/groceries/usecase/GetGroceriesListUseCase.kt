package com.example.recipia.core.domain.groceries.usecase

import com.example.datastore.ShoppingListRepository
import com.example.recipia.core.common.model.GroceriesItem
import com.example.recipia.core.domain.groceries.mappers.GroceriesItemMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetGroceriesListUseCase {
    suspend operator fun invoke(): Flow<List<GroceriesItem>>
}

internal class GetGroceriesListUseCaseImpl @Inject constructor(
    private val repository: ShoppingListRepository,
    private val mapper: GroceriesItemMapper,
) : GetGroceriesListUseCase {
    override suspend operator fun invoke(): Flow<List<GroceriesItem>> {
        return repository.shoppingListFlow.map { list ->
            list.map { mapper.convertToDomain(it) }
        }
    }
}