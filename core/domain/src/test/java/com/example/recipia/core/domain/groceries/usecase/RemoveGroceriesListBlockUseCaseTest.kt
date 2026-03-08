package com.example.recipia.core.domain.groceries.usecase

import com.example.datastore.ShoppingListRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class RemoveGroceriesListBlockUseCaseTest {
    private val mockedRepository = mockk<ShoppingListRepository>()
    private val removeGroceriesListBlockUseCase =
        RemoveGroceriesListBlockUseCaseImpl(mockedRepository)

    @Test
    fun removeGroceriesListBlockUseCase_removesItemWithCorrectIndex() = runTest {
        val testIndex = 5
        coEvery { mockedRepository.removeItem(testIndex) } returns Unit

        removeGroceriesListBlockUseCase(testIndex)

        coVerify(exactly = 1) { mockedRepository.removeItem(testIndex) }
    }
}