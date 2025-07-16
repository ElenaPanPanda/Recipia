package com.example.recipia.feature.recipedetails.impl.domain.mapper

import com.example.datastore.ShoppingListIngredientDatastoreModel
import com.example.datastore.ShoppingListItemDatastoreModel
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredient
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredientSection
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Test

class DetailedIngredientSectionMapperTest {
    private val detailedIngredientMapper = mockk<DetailedIngredientMapper>()
    private val mapper = DetailedIngredientSectionMapperImpl(detailedIngredientMapper)

    @Test
    fun convertToDomainTest() {
        val given = ShoppingListItemDatastoreModel(
            title = "title",
            ingredientsList = listOf(
                ShoppingListIngredientDatastoreModel(
                    amount = "amount",
                    name = "name",
                    isCrossedOut = false
                ),
                ShoppingListIngredientDatastoreModel(
                    amount = "amount2",
                    name = "name2",
                    isCrossedOut = false
                )
            )
        )

        every { detailedIngredientMapper.convertToDomain(any()) } returns mockk()

        val expectedTitle = "title"
        val mapped = mapper.convertToDomain(given)

        assertEquals(expectedTitle, mapped.title)

        verify {
            given.ingredientsList.forEach {
                detailedIngredientMapper.convertToDomain(it)
            }
        }
        confirmVerified(detailedIngredientMapper)
    }

    @Test
    fun convertToDatastoreModelTest() {
        val given = DetailedIngredientSection(
            title = "title",
            ingredientsList = listOf(
                DetailedIngredient(
                    amount = "amount",
                    ingredient = "name",
                    addedToList = false
                ),
                DetailedIngredient(
                    amount = "amount2",
                    ingredient = "name2",
                    addedToList = false
                )
            )
        )

        every { detailedIngredientMapper.convertToDatastoreModel(any()) } returns mockk()

        val expectedTitle = "title"
        val mapped = mapper.convertToDatastoreModel(given)

        assertEquals(expectedTitle, mapped.title)

        verify {
            given.ingredientsList.forEach {
                detailedIngredientMapper.convertToDatastoreModel(it)
            }
        }
        confirmVerified(detailedIngredientMapper)
    }
}