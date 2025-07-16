package com.examplerecipia.feature.groceries.impl.domain.usecase

interface AddListBlockUseCase {
    suspend fun addListBlock(newTitle: String, newValue: String)
}