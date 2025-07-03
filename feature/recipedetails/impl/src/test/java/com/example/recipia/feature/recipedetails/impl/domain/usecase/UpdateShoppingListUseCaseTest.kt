package com.example.recipia.feature.recipedetails.impl.domain.usecase

import com.example.datastore.ShoppingListItemDatastoreModel
import com.example.datastore.ShoppingListRepository
import com.example.recipia.feature.recipedetails.impl.domain.mapper.DetailedIngredientSectionMapper
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredientSection
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Test

class UpdateShoppingListUseCaseTest {
    // mock dependencies
    private val repository = mockk<ShoppingListRepository>()
    private val mapper = mockk<DetailedIngredientSectionMapper>()

    private val usecase = UpdateShoppingListUseCaseImpl(repository, mapper)

    @Test
    fun `update shopping list`() = runTest {
        // I will not check mapper cause it uses another mapper
        val givenItem = DetailedIngredientSection("", emptyList())
        val mappedItem = ShoppingListItemDatastoreModel("", emptyList())

        // rule: this is what the repository should do
        coEvery { repository.updateItem(1, mappedItem) } returns mockk()
        // rule: this is what the mapper should return
        every { mapper.convertToDatastoreModel(givenItem) } returns mappedItem

        // call usecase
        usecase.update(1, givenItem)

        // verify that the repository was called
        coVerify { repository.updateItem(1, mappedItem) }

        // verify that the mapper was called
        verify { mapper.convertToDatastoreModel(givenItem) }
    }
}