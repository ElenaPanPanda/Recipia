package com.example.recipia.core.domain.groceries.usecase

import com.example.datastore.ShoppingListItemDatastoreModel
import com.example.datastore.ShoppingListRepository
import com.example.recipia.core.common.model.IngredientSection
import com.example.recipia.core.domain.groceries.mappers.IngredientMapper
import javax.inject.Inject

interface AddAllIngredientsToGroceriesUseCase {
    suspend operator fun invoke(recipeName: String, ingredients: List<IngredientSection>)
}

internal class AddAllIngredientsToGroceriesUseCaseImpl @Inject constructor(
    private val repository: ShoppingListRepository,
    private val mapper: IngredientMapper
) : AddAllIngredientsToGroceriesUseCase {
    override suspend fun invoke(recipeName: String, ingredients: List<IngredientSection>) {
        val allIngredients = ingredients.flatMap { it.ingredientsList }

        // TODO: check whether we have this title (recipeName) in the DataStore. And, if we have it, do nothing and throw.

        val newValue = ShoppingListItemDatastoreModel(
            title = recipeName,
            ingredientsList = allIngredients.map { mapper.convertToDto(it) }
        )
        repository.addItem(newValue)
    }
}