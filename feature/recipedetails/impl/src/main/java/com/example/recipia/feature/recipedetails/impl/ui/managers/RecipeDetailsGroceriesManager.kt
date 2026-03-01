package com.example.recipia.feature.recipedetails.impl.ui.managers

import com.example.recipia.feature.recipedetails.impl.domain.usecase.AddAllIngredientsToShoppingListUseCase
import com.example.recipia.feature.recipedetails.impl.domain.usecase.CheckAddedIngredientsInShoppingListUseCase
import javax.inject.Inject

class RecipeDetailsGroceriesManager @Inject constructor(
    private val checkAddedIngredientsInShoppingListUseCase: CheckAddedIngredientsInShoppingListUseCase,
    private val addAllIngredientsToShoppingListUseCase: AddAllIngredientsToShoppingListUseCase,
) {
}