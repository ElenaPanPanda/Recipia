package com.example.recipia.core.domain.groceries.usecase

import com.example.datastore.ShoppingListItemDatastoreModel
import com.example.datastore.ShoppingListRepository
import com.example.recipia.core.common.model.GroceriesItem
import com.example.recipia.core.domain.groceries.GroceriesDomainTestHelper.createGroceriesIngredient
import com.example.recipia.core.domain.groceries.mappers.GroceriesItemMapper
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class RemoveCheckedGroceriesItemsUseCaseTest {
    private val mockedRepository = mockk<ShoppingListRepository>(relaxed = true)
    private val mockedMapper = mockk<GroceriesItemMapper>()
    private val removeCheckedGroceriesItemsUseCase =
        RemoveCheckedGroceriesItemsUseCaseImpl(mockedRepository, mockedMapper)

    @Test
    fun removeCheckedGroceriesItemsUseCase_allIngredientsCrossedOut_removeAll() = runTest {
        val list = listOf(
            GroceriesItem(
                "title",
                ingredientsList = listOf(createGroceriesIngredient(isCrossedOut = true))
            )
        )

        removeCheckedGroceriesItemsUseCase(list)

        coVerify(exactly = 1) { mockedRepository.removeItem(0) }
    }

    @Test
    fun removeCheckedGroceriesItemsUseCase_someIngredientsCrossedOut_updatesGroceriesItem() =
        runTest {
            val list = listOf(
                GroceriesItem(
                    "title",
                    ingredientsList = listOf(
                        createGroceriesIngredient(name = "Checked", isCrossedOut = true),
                        createGroceriesIngredient(name = "Remaining", isCrossedOut = false)
                    )
                )
            )
            val mockDto = mockk<ShoppingListItemDatastoreModel>()
            every { mockedMapper.convertToDto(any()) } returns mockDto

            removeCheckedGroceriesItemsUseCase(list)

            coVerify(exactly = 1) { mockedRepository.updateItem(0, mockDto) }
        }

    @Test
    fun removeCheckedGroceriesItemsUseCase_multipleItems_removingLasItemDoesntBreakFirstItem() = runTest {
        val list = listOf(
            GroceriesItem("First", listOf(createGroceriesIngredient(isCrossedOut = true))),
            GroceriesItem("Second", listOf(createGroceriesIngredient(isCrossedOut = true)))
        )

        removeCheckedGroceriesItemsUseCase(list)

        coVerify(ordering = io.mockk.Ordering.SEQUENCE) {
            mockedRepository.removeItem(1)
            mockedRepository.removeItem(0)
        }
    }
}