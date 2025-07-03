package com.example.recipia.feature.recipedetails.impl.domain.usecase

import app.cash.turbine.test
import com.example.datastore.ShoppingListIngredientDatastoreModel
import com.example.datastore.ShoppingListItemDatastoreModel
import com.example.datastore.ShoppingListRepository
import com.example.recipia.feature.recipedetails.impl.domain.mapper.DetailedIngredientMapper
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredient
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class CheckAddedIngredientsInShoppingListUseCaseTest {
    // mock dependencies
    private val repository = mockk<ShoppingListRepository>()
    private val mapper = mockk<DetailedIngredientMapper>()

    private val useCase = CheckAddedIngredientsInShoppingListUseCaseImpl(repository, mapper)

    @Test
    fun `returns ingredients mapped for given recipe name`() = runTest {
        // given
        val recipeName = "recipeName"

        val givenIngredient1 = ShoppingListIngredientDatastoreModel("1 cup", "Tomatoes", false)
        val givenIngredient2 = ShoppingListIngredientDatastoreModel("2 tsp", "Salt", false)

        // list returned from repository
        val listFromRepo = listOf(
            ShoppingListItemDatastoreModel(
                title = recipeName,
                ingredientsList = listOf(givenIngredient1, givenIngredient2)
            ),
            ShoppingListItemDatastoreModel(
                title = "Another",
                ingredientsList = emptyList()
            )
        )

        // expected result
        val expectedIngredient1 = DetailedIngredient("1 cup", "Tomatoes", true)
        val expectedIngredient2 = DetailedIngredient("2 tsp", "Salt", true)

        // rule: this is what the repository should return this list each time
        coEvery { repository.shoppingListFlow } returns flowOf(listFromRepo)

        // rule: this is what the mapper should return this ingredients
        every { mapper.convertToDomain(givenIngredient1) } returns expectedIngredient1
        every { mapper.convertToDomain(givenIngredient2) } returns expectedIngredient2

        // call usecase -> collect the flow ( Turbine )
        useCase.getAddedIngredients(recipeName).test {
            val result = awaitItem() // waits for the first emitted value
            assertEquals(listOf(expectedIngredient1, expectedIngredient2), result)
            cancelAndIgnoreRemainingEvents()
        }

        // verify that the mapper was called for each ingredient in the usecase
        verify {
            listFromRepo.forEach { item ->
                item.ingredientsList.forEach {
                    mapper.convertToDomain(it)
                }
            }
        }
    }

    @Test
    fun `returns empty list if recipe name not found`() = runTest {
        val recipeName = "recipeName"
        coEvery { repository.shoppingListFlow } returns flowOf(
            listOf(ShoppingListItemDatastoreModel("title", emptyList()))
        )

        useCase.getAddedIngredients(recipeName).test {
            val result = awaitItem()
            val expected = emptyList<DetailedIngredient>()
            assertEquals(expected, result)
            cancelAndIgnoreRemainingEvents()
        }
    }
}