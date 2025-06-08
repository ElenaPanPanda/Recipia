package com.examplerecipia.feature.groceries.impl.domain.usecase

interface AddIngredientToListBlockUseCase {
    suspend fun updateListBlock(index: Int, newValue: String)
}