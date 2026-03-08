package com.example.recipia.core.domain.groceries.usecase

import com.example.datastore.ShoppingListRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ClearGroceriesUseCaseTest {
    private val mockedRepository = mockk<ShoppingListRepository>()
    private val clearGroceriesUseCase = ClearGroceriesUseCaseImpl(mockedRepository)

    @Test
    fun clearGroceriesUseCase_callsRepository() = runTest {
        coEvery { mockedRepository.clear() } returns Unit

        clearGroceriesUseCase()

        coVerify(exactly = 1) { mockedRepository.clear() }
    }
}