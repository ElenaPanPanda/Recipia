package com.example.recipia.core.domain.groceries.mappers

import com.example.recipia.core.domain.groceries.GroceriesDomainTestHelper.createGroceriesIngredient
import com.example.recipia.core.domain.groceries.GroceriesDomainTestHelper.createGroceriesItem
import com.example.recipia.core.domain.groceries.GroceriesDomainTestHelper.createShoppingListIngredientDatastoreModel
import com.example.recipia.core.domain.groceries.GroceriesDomainTestHelper.createShoppingListItemDatastoreModel
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class GroceriesItemMapperTest {
    private val mockedGroceriesIngredientsMapper = mockk<GroceriesIngredientMapper>()
    private val mapper = GroceriesItemMapperImpl(mockedGroceriesIngredientsMapper)

    @Test
    fun convertToDomain_convertsCorrectly() {
        val dto = createShoppingListItemDatastoreModel()
        val ingredientDto = dto.ingredientsList.first()
        val expectedIngredient = createGroceriesIngredient()
        val expected = createGroceriesItem(ingredientsList = listOf(expectedIngredient))

        every { mockedGroceriesIngredientsMapper.convertToDomain(ingredientDto) } returns expectedIngredient

        val result = mapper.convertToDomain(dto)

        assertThat(result).isEqualTo(expected)
        verify(exactly = 1) { mockedGroceriesIngredientsMapper.convertToDomain(ingredientDto) }
    }

    @Test
    fun convertToDto_convertsCorrectly() {
        val ingredient = createGroceriesIngredient()
        val domain = createGroceriesItem(ingredientsList = listOf(ingredient))
        val expectedIngredientDto = createShoppingListIngredientDatastoreModel()
        val expected = createShoppingListItemDatastoreModel(ingredientsList = listOf(expectedIngredientDto))

        every { mockedGroceriesIngredientsMapper.convertToDto(ingredient) } returns expectedIngredientDto

        val result = mapper.convertToDto(domain)

        assertThat(result).isEqualTo(expected)
        verify(exactly = 1) { mockedGroceriesIngredientsMapper.convertToDto(ingredient) }
    }
}