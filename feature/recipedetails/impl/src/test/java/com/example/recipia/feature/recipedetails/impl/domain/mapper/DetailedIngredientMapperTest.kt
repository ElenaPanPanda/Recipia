package com.example.recipia.feature.recipedetails.impl.domain.mapper

import com.example.datastore.ShoppingListIngredientDatastoreModel
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredient
import org.junit.Assert.assertEquals
import org.junit.Test

class DetailedIngredientMapperTest {
    private val mapper = DetailedIngredientMapperImpl()

    @Test
    fun convertToDatastoreModelTest() {
        val detailedIngredient = DetailedIngredient(
            amount = "1",
            ingredient = "ingredient",
            addedToList = true
        )
        val detailedIngredient2 = DetailedIngredient(
            amount = "1",
            ingredient = "ingredient",
            addedToList = false
        )
        val expected = ShoppingListIngredientDatastoreModel(
            amount = "1",
            name = "ingredient",
            isCrossedOut = false
        )

        assertEquals(expected, mapper.convertToDatastoreModel(detailedIngredient))
        assertEquals(expected, mapper.convertToDatastoreModel(detailedIngredient2))
    }

    @Test
    fun convertToDomainTest() {
        val detailedIngredient = ShoppingListIngredientDatastoreModel(
            amount = "1",
            name = "ingredient",
            isCrossedOut = true
        )
        val detailedIngredient2 = ShoppingListIngredientDatastoreModel(
            amount = "1",
            name = "ingredient",
            isCrossedOut = false
        )
        val expected = DetailedIngredient(
            amount = "1",
            ingredient = "ingredient",
            addedToList = true
        )

        assertEquals(expected, mapper.convertToDomain(detailedIngredient))
        assertEquals(expected, mapper.convertToDomain(detailedIngredient2))
    }
}