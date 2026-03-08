package com.example.recipia.core.domain.groceries.usecase

import com.example.datastore.ShoppingListItemDatastoreModel
import com.example.datastore.ShoppingListRepository
import com.example.recipia.core.common.model.GroceriesItem
import com.example.recipia.core.domain.groceries.mappers.GroceriesItemMapper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class UpdateGroceriesListBlockUseCaseTest {
    private val mockedRepository = mockk<ShoppingListRepository>()
    private val mockedMapper = mockk<GroceriesItemMapper>()
    private val updateGroceriesListBlockUseCase = UpdateGroceriesListBlockUseCaseImpl(
        repository = mockedRepository,
        mapper = mockedMapper
    )

    @Test
    fun updateGroceriesListBlockUseCase_callsRepositoryWithMappedDtoAndCorrectIndex() = runTest {
        val testIndex = 2
        val mockDto = mockk<ShoppingListItemDatastoreModel>()
        val mockDomainItem = mockk<GroceriesItem>()
        every { mockedMapper.convertToDto(mockDomainItem) } returns mockDto
        coEvery { mockedRepository.updateItem(testIndex, mockDto) } returns Unit

        updateGroceriesListBlockUseCase(testIndex, mockDomainItem)

        every { mockedMapper.convertToDto(mockDomainItem) }
        coVerify(exactly = 1) { mockedRepository.updateItem(testIndex, mockDto) }
    }
}