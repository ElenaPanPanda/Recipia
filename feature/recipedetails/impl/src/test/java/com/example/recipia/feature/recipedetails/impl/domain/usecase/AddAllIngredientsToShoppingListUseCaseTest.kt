package com.example.recipia.feature.recipedetails.impl.domain.usecase

import com.example.datastore.ShoppingListIngredientDatastoreModel
import com.example.datastore.ShoppingListItemDatastoreModel
import com.example.datastore.ShoppingListRepository
import com.example.recipia.feature.recipedetails.impl.domain.mapper.DetailedIngredientMapper
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredient
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredientSection
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AddAllIngredientsToShoppingListUseCaseTest {
    // mock
    private val shoppingListRepository = mockk<ShoppingListRepository>()
    private val mapper = mockk<DetailedIngredientMapper>()

    private val useCase = AddAllIngredientsToShoppingListUseCaseImpl(shoppingListRepository, mapper)

    @Test
    fun `add ingredients to shopping list`() = runTest {
        val recipeName = "recipeName"

        // two ingredients for the list
        val givenIngredient1 = DetailedIngredient(
            amount = "amount1",
            ingredient = "ingredient1",
            addedToList = false
        )
        val givenIngredient2 = DetailedIngredient(
            amount = "amount2",
            ingredient = "ingredient2",
            addedToList = true
        )
        // list which is passed to the usecase
        val detailedIngredientSections = listOf(
            DetailedIngredientSection(
                title = "title1",
                ingredientsList = listOf(element = givenIngredient1)
            ),
            DetailedIngredientSection(
                title = "title2",
                ingredientsList = listOf(element = givenIngredient2)
            )
        )

        // expected results (to check that the mapper was called)
        val expectedIngredient1 = ShoppingListIngredientDatastoreModel(
            amount = "amount1",
            name = "ingredient1",
            isCrossedOut = false
        )
        val expectedIngredient2 = ShoppingListIngredientDatastoreModel(
            amount = "amount2",
            name = "ingredient2",
            isCrossedOut = true
        )
        val expectedItem = ShoppingListItemDatastoreModel(
            title = "recipeName",
            ingredientsList = listOf(expectedIngredient1, expectedIngredient2)
        )

        // rule: this is what the mapper should return this ingredients
        every { mapper.convertToDatastoreModel(givenIngredient1) } returns expectedIngredient1
        every { mapper.convertToDatastoreModel(givenIngredient2) } returns expectedIngredient2

        // rule: this is what the repository should do
        coEvery { shoppingListRepository.addItem(any()) } returns Unit

        // call usecase
        useCase.add(recipeName, detailedIngredientSections)

        // verify that the mapper was called for each ingredient in the usecase
        // (and that the mapper returns the expected values)
        verify {
            detailedIngredientSections.forEach { section ->
                section.ingredientsList.forEach {
                    mapper.convertToDatastoreModel(it)
                }
            }
        }
        // verify that the repository was called with the expected item
        coVerify(exactly = 1) { shoppingListRepository.addItem(expectedItem) }
    }
}