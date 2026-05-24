package com.example.recipia.core.domain.groceries.usecase

import com.example.datastore.ShoppingListItemDatastoreModel
import com.example.datastore.ShoppingListRepository
import com.example.recipia.core.common.model.GroceriesItem
import com.example.recipia.core.domain.groceries.mappers.GroceriesItemMapper
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetGroceriesUseCaseTest {
    private val mockedRepository = mockk<ShoppingListRepository>()
    private val mockedMapper = mockk<GroceriesItemMapper>()
    private val getGroceriesListUseCase =
        GetGroceriesListUseCaseImpl(mockedRepository, mockedMapper)

    @Test
    fun getGroceriesListUseCase_returnsMappedGroceries() = runTest {
        val mockDto = mockk<ShoppingListItemDatastoreModel>()
        val mockDomainItem = mockk<GroceriesItem>()

        every { mockedRepository.shoppingListFlow } returns flowOf(listOf(mockDto))
        every { mockedMapper.convertToDomain(mockDto) } returns mockDomainItem

        val resultFlow = getGroceriesListUseCase()
        val resultList = resultFlow.first()

        assertThat(resultList.size).isEqualTo(1)
        assertThat(resultList[0]).isEqualTo(mockDomainItem)
    }
}