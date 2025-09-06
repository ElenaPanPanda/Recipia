package com.example.recipia.feature.recipedetails.impl.domain.usecase

import com.example.datastore.ShoppingListItemDatastoreModel
import com.example.datastore.ShoppingListRepository
import com.example.recipia.feature.recipedetails.impl.domain.mapper.DetailedIngredientSectionMapper
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredient
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredientSection
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AddIngredientToShoppingListTest {
    // mock dependencies
    private val repository = mockk<ShoppingListRepository>(relaxed = true)
    private val mapper = mockk<DetailedIngredientSectionMapper>()

    private val useCase = AddIngredientToShoppingListImpl(repository, mapper)

    @Test
    fun `adds new ingredient section if it is NOT in the shopping list`() = runTest {
        // GIVEN a new ingredient for a recipe that isn't in the shopping list yet
        val recipeName = "Pizza"
        val newIngredient = DetailedIngredient("1 cup", "Flour", addedToList = false)
        val newSectionDomain = DetailedIngredientSection(recipeName, listOf(newIngredient))
        val newSectionDatastore = ShoppingListItemDatastoreModel(
            recipeName,
            emptyList()
        ) // Content doesn't matter, just the object reference

        // GIVEN the shopping list is initially empty
        coEvery { repository.shoppingListFlow } returns flowOf(emptyList())
        // GIVEN the mapper will convert the new section correctly
        every { mapper.convertToDatastoreModel(newSectionDomain) } returns newSectionDatastore

        // WHEN the add method is called
        useCase.add(recipeName, newIngredient)

        // THEN a new item should be added to the repository
        coVerify(exactly = 1) { repository.addItem(newSectionDatastore) }
        // THEN no items should be updated
        coVerify(exactly = 0) { repository.updateItem(any(), any()) }
    }

    @Test
    fun `updates ingredients section if it IS in the shopping list`() = runTest {
        // GIVEN an existing ingredient and a new one to add
        val recipeName = "Pizza"
        val existingIngredient = DetailedIngredient("1 cup", "Flour", addedToList = false)
        val newIngredient = DetailedIngredient("1 tsp", "Yeast", addedToList = false)

        // GIVEN the shopping list already contains a section for the recipe
        val existingSectionDomain =
            DetailedIngredientSection(recipeName, listOf(existingIngredient))
        val existingSectionDatastore = ShoppingListItemDatastoreModel(recipeName, emptyList())
        coEvery { repository.shoppingListFlow } returns flowOf(listOf(existingSectionDatastore))

        // GIVEN the mappers are set up for conversion
        every { mapper.convertToDomain(existingSectionDatastore) } returns existingSectionDomain
        val updatedSectionDomain =
            DetailedIngredientSection(recipeName, listOf(existingIngredient, newIngredient))
        val updatedSectionDatastore = ShoppingListItemDatastoreModel(recipeName, emptyList())
        every { mapper.convertToDatastoreModel(updatedSectionDomain) } returns updatedSectionDatastore

        // WHEN the add method is called with the new ingredient
        useCase.add(recipeName, newIngredient)

        // THEN the existing item should be updated in the repository at the correct index (0)
        coVerify(exactly = 1) { repository.updateItem(0, updatedSectionDatastore) }
        // THEN no new items should be added
        coVerify(exactly = 0) { repository.addItem(any()) }
    }

    @Test
    fun `does not update shopping list if there is an existing ingredient`() = runTest {
        // GIVEN an ingredient that is already in the shopping list
        val recipeName = "Pizza"
        val existingIngredient = DetailedIngredient("1 cup", "Flour", addedToList = false)

        // GIVEN the shopping list already contains that ingredient
        val existingSectionDomain =
            DetailedIngredientSection(recipeName, listOf(existingIngredient))
        val existingSectionDatastore = ShoppingListItemDatastoreModel(recipeName, emptyList())
        coEvery { repository.shoppingListFlow } returns flowOf(listOf(existingSectionDatastore))
        every { mapper.convertToDomain(existingSectionDatastore) } returns existingSectionDomain

        // WHEN the add method is called with the same ingredient
        useCase.add(recipeName, existingIngredient)

        // THEN the repository should NOT be modified
        coVerify(exactly = 0) { repository.addItem(any()) }
        coVerify(exactly = 0) { repository.updateItem(any(), any()) }
        // THEN the domain-to-datastore mapper should not be called, as the logic exits early
        verify(exactly = 0) { mapper.convertToDatastoreModel(any()) }
    }
}