package com.example.recipia.core.domain.groceries.mappers

import com.example.recipia.core.domain.groceries.GroceriesDomainTestHelper.createGroceriesIngredient
import com.example.recipia.core.domain.groceries.GroceriesDomainTestHelper.createShoppingListIngredientDatastoreModel
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class GroceriesIngredientMapperTest {
    private val mapper = GroceriesIngredientMapperImpl()

    @Test
    fun convertToDomain_convertsCorrectly() {
        val dto = createShoppingListIngredientDatastoreModel()
        val expectedDomain = createGroceriesIngredient()

        val result = mapper.convertToDomain(dto)

        assertThat(result).isEqualTo(expectedDomain)
    }

    @Test
    fun convertToDto_convertsCorrectly() {
        val domain = createGroceriesIngredient()
        val expectedDto = createShoppingListIngredientDatastoreModel()

        val result = mapper.convertToDto(domain)

        assertThat(result).isEqualTo(expectedDto)
    }
}