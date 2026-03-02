package com.example.recipia.feature.recipedetails.impl.domain.usecase

import com.example.datastore.ShoppingListRepository
import com.example.recipia.feature.recipedetails.impl.domain.mapper.DetailedIngredientSectionMapper
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredient
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredientSection
import kotlinx.coroutines.flow.first
import javax.inject.Inject

internal class AddIngredientToShoppingListUseCaseImpl @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository,
    private val mapper: DetailedIngredientSectionMapper,
) : AddIngredientToShoppingListUseCase {
    override suspend fun add(recipeName: String, ingredient: DetailedIngredient) {
        val currentShoppingList = shoppingListRepository.shoppingListFlow.first()

        val existingSectionIndex = currentShoppingList.indexOfFirst { it.title == recipeName }

        if (existingSectionIndex != -1) {
            // A section for this recipe already exists, update it.
            val existingSectionDatastore = currentShoppingList[existingSectionIndex]
            val existingSectionDomain = mapper.convertToDomain(existingSectionDatastore)

            // Check if this exact ingredient is already in the list to avoid duplicates.
            if (!existingSectionDomain.ingredientsList.contains(ingredient)) {
                val updatedIngredients = existingSectionDomain.ingredientsList + ingredient
                val updatedSectionDomain =
                    existingSectionDomain.copy(ingredientsList = updatedIngredients)
                val updatedSectionDataStore = mapper.convertToDatastoreModel(updatedSectionDomain)

                shoppingListRepository.updateItem(existingSectionIndex, updatedSectionDataStore)
            }
        } else {
            // No section for this recipe exists, create a new one.
            val newSection = DetailedIngredientSection(
                title = recipeName,
                ingredientsList = listOf(ingredient)
            )
            val newSectionDataStore = mapper.convertToDatastoreModel(newSection)
            shoppingListRepository.addItem(newSectionDataStore)
        }
    }
}