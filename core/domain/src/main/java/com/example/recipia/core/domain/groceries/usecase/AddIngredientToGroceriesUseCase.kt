package com.example.recipia.core.domain.groceries.usecase

import com.example.datastore.ShoppingListItemDatastoreModel
import com.example.datastore.ShoppingListRepository
import com.example.recipia.core.common.model.Ingredient
import com.example.recipia.core.common.model.IngredientSection
import com.example.recipia.core.domain.groceries.mappers.IngredientSectionMapper
import kotlinx.coroutines.flow.first
import javax.inject.Inject

interface AddIngredientToGroceriesUseCase {
    suspend operator fun invoke(recipeName: String, ingredient: Ingredient)
}

internal class AddIngredientToGroceriesUseCaseImpl @Inject constructor(
    private val repository: ShoppingListRepository,
    private val mapper: IngredientSectionMapper
) : AddIngredientToGroceriesUseCase {
    override suspend fun invoke(recipeName: String, ingredient: Ingredient) {
        val currentShoppingList = repository.shoppingListFlow.first()

        val existingSectionIndex = currentShoppingList.indexOfFirst { it.title == recipeName }

        if (existingSectionIndex != -1) {
            updateExistingSection(currentShoppingList, existingSectionIndex, ingredient)
        } else {
            createNewSection(recipeName, ingredient)
        }
    }

    private suspend fun createNewSection(
        recipeName: String,
        ingredient: Ingredient
    ) {
        val newSection = IngredientSection(
            title = recipeName,
            ingredientsList = listOf(ingredient)
        )
        val newSectionDataStore = mapper.convertToDto(newSection)
        repository.addItem(newSectionDataStore)
    }

    private suspend fun updateExistingSection(
        currentShoppingList: List<ShoppingListItemDatastoreModel>,
        existingSectionIndex: Int,
        ingredient: Ingredient
    ) {
        val existingSectionDatastore = currentShoppingList[existingSectionIndex]
        val existingSectionDomain = mapper.convertToDomain(existingSectionDatastore)

        // Check if this exact ingredient is already in the list to avoid duplicates.
        if (existingSectionDomain.ingredientsList.contains(ingredient)) return

        val updatedIngredients = existingSectionDomain.ingredientsList + ingredient
        val updatedSectionDomain =
            existingSectionDomain.copy(ingredientsList = updatedIngredients)
        val updatedSectionDataStore = mapper.convertToDto(updatedSectionDomain)

        repository.updateItem(existingSectionIndex, updatedSectionDataStore)
    }
}