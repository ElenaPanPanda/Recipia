package com.example.recipia.core.domain.groceries.usecase

import com.example.datastore.ShoppingListItemDatastoreModel
import com.example.datastore.ShoppingListRepository
import com.example.recipia.core.domain.groceries.mappers.GroceriesItemMapper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AddGroceriesBlockUseCaseTest {
    private val mockedRepository = mockk<ShoppingListRepository>()
    private val mockedMapper = mockk<GroceriesItemMapper>()
    private val addListBlockUseCase = AddGroceriesBlockUseCaseImpl(mockedRepository, mockedMapper)

    @Test
    fun addGroceriesBlockUseCase_callsRepository() = runTest {
        val newTitle = "New title"
        val newValue = "New value"
        val mockDto = mockk<ShoppingListItemDatastoreModel>()

        every { mockedMapper.convertToDto(any()) } returns mockDto
        coEvery { mockedRepository.addItem(any()) } returns Unit

        addListBlockUseCase(newTitle, newValue)

        every { mockedMapper.convertToDto(match { it.title == newTitle }) }
        coVerify(exactly = 1) { mockedRepository.addItem(mockDto) }
    }
}