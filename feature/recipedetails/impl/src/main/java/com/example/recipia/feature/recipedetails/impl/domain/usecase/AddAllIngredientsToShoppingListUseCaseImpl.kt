package com.example.recipia.feature.recipedetails.impl.domain.usecase

import com.example.datastore.ShoppingListItemDatastoreModel
import com.example.datastore.ShoppingListRepository
import com.example.recipia.feature.recipedetails.impl.domain.mapper.DetailedIngredientMapper
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredientSection
import javax.inject.Inject

internal class AddAllIngredientsToShoppingListUseCaseImpl @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository,
    private val mapper: DetailedIngredientMapper
): AddAllIngredientsToShoppingListUseCase {
    override suspend fun add(recipeName: String, ingredients: List<DetailedIngredientSection>) {
        val allIngredients = ingredients.flatMap { it.ingredientsList }

        val newValue = ShoppingListItemDatastoreModel(
            title = recipeName,
            ingredientsList = allIngredients.map { mapper.convertToDatastoreModel(it) }
        )
        shoppingListRepository.addItem(newValue)
    }
}