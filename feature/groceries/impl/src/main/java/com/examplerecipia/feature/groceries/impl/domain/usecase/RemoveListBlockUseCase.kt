package com.examplerecipia.feature.groceries.impl.domain.usecase

interface RemoveListBlockUseCase {
    suspend fun removeListBlock(index: Int)
}