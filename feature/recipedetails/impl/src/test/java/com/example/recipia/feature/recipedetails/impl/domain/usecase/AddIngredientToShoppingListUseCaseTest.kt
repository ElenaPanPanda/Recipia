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

class AddIngredientToShoppingListUseCaseTest {
    private val repository = mockk<ShoppingListRepository>(relaxed = true)
    private val mapper = mockk<DetailedIngredientSectionMapper>()

    private val useCase = AddIngredientToShoppingListUseCaseImpl(repository, mapper)

    @Test
    fun `adds new ingredient section if it is NOT in the shopping list`() = runTest {
        val recipeName = "Pizza"
        val newIngredient = DetailedIngredient("1 cup", "Flour", addedToList = false)
        val newSectionDomain = DetailedIngredientSection(recipeName, listOf(newIngredient))
        val newSectionDatastore = ShoppingListItemDatastoreModel(
            recipeName,
            emptyList()
        )

        coEvery { repository.shoppingListFlow } returns flowOf(emptyList())
        every { mapper.convertToDatastoreModel(newSectionDomain) } returns newSectionDatastore

        useCase.add(recipeName, newIngredient)

        coVerify(exactly = 1) { repository.addItem(newSectionDatastore) }
        coVerify(exactly = 0) { repository.updateItem(any(), any()) }
    }

    @Test
    fun `updates ingredients section if it IS in the shopping list`() = runTest {
        val recipeName = "Pizza"
        val existingIngredient = DetailedIngredient("1 cup", "Flour", addedToList = false)
        val newIngredient = DetailedIngredient("1 tsp", "Yeast", addedToList = false)

        val existingSectionDomain =
            DetailedIngredientSection(recipeName, listOf(existingIngredient))
        val existingSectionDatastore = ShoppingListItemDatastoreModel(recipeName, emptyList())
        coEvery { repository.shoppingListFlow } returns flowOf(listOf(existingSectionDatastore))

        every { mapper.convertToDomain(existingSectionDatastore) } returns existingSectionDomain
        val updatedSectionDomain =
            DetailedIngredientSection(recipeName, listOf(existingIngredient, newIngredient))
        val updatedSectionDatastore = ShoppingListItemDatastoreModel(recipeName, emptyList())
        every { mapper.convertToDatastoreModel(updatedSectionDomain) } returns updatedSectionDatastore

        useCase.add(recipeName, newIngredient)

        coVerify(exactly = 1) { repository.updateItem(0, updatedSectionDatastore) }
        coVerify(exactly = 0) { repository.addItem(any()) }
    }

    @Test
    fun `does not update shopping list if there is an existing ingredient`() = runTest {
        val recipeName = "Pizza"
        val existingIngredient = DetailedIngredient("1 cup", "Flour", addedToList = false)

        val existingSectionDomain =
            DetailedIngredientSection(recipeName, listOf(existingIngredient))
        val existingSectionDatastore = ShoppingListItemDatastoreModel(recipeName, emptyList())
        coEvery { repository.shoppingListFlow } returns flowOf(listOf(existingSectionDatastore))
        every { mapper.convertToDomain(existingSectionDatastore) } returns existingSectionDomain

        useCase.add(recipeName, existingIngredient)

        coVerify(exactly = 0) { repository.addItem(any()) }
        coVerify(exactly = 0) { repository.updateItem(any(), any()) }
        verify(exactly = 0) { mapper.convertToDatastoreModel(any()) }
    }
}