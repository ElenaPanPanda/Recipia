package com.example.recipia.feature.recipedetails.impl.domain.usecase

import com.example.datastore.ShoppingListItemDatastoreModel
import com.example.datastore.ShoppingListRepository
import com.example.recipia.feature.recipedetails.impl.domain.mapper.DetailedIngredientMapper
import com.example.recipia.feature.recipedetails.impl.domain.mapper.DetailedIngredientSectionMapper
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredientSection
import kotlinx.coroutines.flow.first
import javax.inject.Inject

internal class AddAllIngredientsToShoppingListUseCaseImpl @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository,
    private val mapper: DetailedIngredientMapper,
    private val sectionMapper: DetailedIngredientSectionMapper,
) : AddAllIngredientsToShoppingListUseCase {
    override suspend fun add(recipeName: String, ingredients: List<DetailedIngredientSection>) {
        val currentShoppingList = shoppingListRepository.shoppingListFlow.first()
        val existingSectionIndex = currentShoppingList.indexOfFirst { it.title == recipeName }

        // The case when the recipe is already in the list
        if (existingSectionIndex != -1) {
            // update the existing section
            val existingSectionDatastore = currentShoppingList[existingSectionIndex]
            val existingSectionDomain = sectionMapper.convertToDomain(existingSectionDatastore)

            val allIngredients = ingredients.flatMap { it.ingredientsList }
            val updatedSectionDomain = existingSectionDomain.copy(ingredientsList = allIngredients)
            val updatedSectionDataStore =
                sectionMapper.convertToDatastoreModel(updatedSectionDomain)
            shoppingListRepository.updateItem(existingSectionIndex, updatedSectionDataStore)

            // The case when the recipe is not in the list
        } else {
            val allIngredients = ingredients.flatMap { it.ingredientsList }

            val newValue = ShoppingListItemDatastoreModel(
                title = recipeName,
                ingredientsList = allIngredients.map { mapper.convertToDatastoreModel(it) }
            )
            shoppingListRepository.addItem(newValue)
        }
    }
}