package com.example.recipia.feature.recipedetails.impl.domain.usecase

import com.example.datastore.ShoppingListRepository
import com.example.recipia.feature.recipedetails.impl.domain.mapper.DetailedIngredientMapper
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class CheckAddedIngredientsInShoppingListUseCaseImpl @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository,
    private val mapper: DetailedIngredientMapper
) : CheckAddedIngredientsInShoppingListUseCase {
    override suspend fun getAddedIngredients(recipeName: String): Flow<List<DetailedIngredient>> {
        return shoppingListRepository.shoppingListFlow.map { list ->
            list
                .firstOrNull { it.title == recipeName }
                ?.ingredientsList
                ?.map { mapper.convertToDomain(it) }
                ?: emptyList()
        }
    }
}