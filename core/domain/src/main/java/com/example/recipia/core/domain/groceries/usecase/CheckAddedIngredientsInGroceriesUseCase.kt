package com.example.recipia.core.domain.groceries.usecase

import com.example.datastore.ShoppingListRepository
import com.example.recipia.core.common.model.Ingredient
import com.example.recipia.core.domain.groceries.mappers.IngredientMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface CheckAddedIngredientsInGroceriesUseCase {
    suspend operator fun invoke(recipeName: String): Flow<List<Ingredient>>
}

internal class CheckAddedIngredientsInGroceriesUseCaseImpl @Inject constructor(
    private val repository: ShoppingListRepository,
    private val mapper: IngredientMapper
) : CheckAddedIngredientsInGroceriesUseCase {
    override suspend fun invoke(recipeName: String): Flow<List<Ingredient>> {
        return repository.shoppingListFlow.map { list ->
            list
                .firstOrNull { it.title == recipeName }
                ?.ingredientsList
                ?.map { mapper.convertToDomain(it) }
                ?: emptyList()
        }
    }
}