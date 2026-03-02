package com.example.recipia.feature.recipedetails.impl.ui.managers

import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredient
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredientSection
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedRecipe
import com.example.recipia.feature.recipedetails.impl.domain.usecase.AddAllIngredientsToShoppingListUseCase
import com.example.recipia.feature.recipedetails.impl.domain.usecase.AddIngredientToShoppingListUseCase
import com.example.recipia.feature.recipedetails.impl.domain.usecase.CheckAddedIngredientsInShoppingListUseCase
import com.example.recipia.feature.recipedetails.impl.ui.RecipeDetailsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecipeDetailsGroceriesManager @Inject constructor(
    private val checkAddedIngredientsInShoppingListUseCase: CheckAddedIngredientsInShoppingListUseCase,
    private val addAllIngredientsToShoppingListUseCase: AddAllIngredientsToShoppingListUseCase,
    private val addIngredientToShoppingListUseCase: AddIngredientToShoppingListUseCase,
) {
    fun observeCheckedIngredients(
        recipe: DetailedRecipe,
        scope: CoroutineScope,
        updateState: (RecipeDetailsState.Success.() -> RecipeDetailsState.Success) -> Unit
    ) {
        scope.launch {
            checkAddedIngredientsInShoppingListUseCase
                .getAddedIngredients(recipe.title)
                .collect { checkedIngredients ->

                    val allIngredientsAmount = recipe.ingredients
                        .flatMap { it.ingredientsList }
                        .size

                    val addedNames = checkedIngredients.map { it.ingredient }.toSet()

                    updateState {
                        val updatedIngredients = this.recipe.ingredients.map { section ->
                            section.copy(
                                ingredientsList = section.ingredientsList.map { ingredient ->
                                    ingredient.copy(
                                        addedToList = addedNames.contains(ingredient.ingredient)
                                    )
                                }
                            )
                        }

                        val updatedRecipe = this.recipe.copy(ingredients = updatedIngredients)

                        copy(
                            recipe = updatedRecipe,
                            isAllIngredientsChecked = checkedIngredients.size == allIngredientsAmount
                        )
                    }
                }
        }
    }

    fun addAllIngredientsToShoppingList(
        recipeName: String,
        ingredients: List<DetailedIngredientSection>,
        scope: CoroutineScope
    ) {
        scope.launch {
            addAllIngredientsToShoppingListUseCase.add(
                recipeName = recipeName,
                ingredients = ingredients
            )
        }
    }

    fun addIngredientToShoppingList(
        recipeName: String,
        ingredient: DetailedIngredient,
        scope: CoroutineScope
    ) {
        scope.launch {
            addIngredientToShoppingListUseCase.add(
                recipeName = recipeName,
                ingredient = ingredient
            )
        }
    }
}