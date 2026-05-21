package com.example.recipia.feature.recipedetails.impl.ui.managers

import com.example.recipia.core.common.model.FullRecipe
import com.example.recipia.core.common.model.Ingredient
import com.example.recipia.core.common.model.IngredientSection
import com.example.recipia.core.domain.groceries.usecase.AddAllIngredientsToGroceriesUseCase
import com.example.recipia.core.domain.groceries.usecase.AddIngredientToGroceriesUseCase
import com.example.recipia.core.domain.groceries.usecase.CheckAddedIngredientsInGroceriesUseCase
import com.example.recipia.feature.recipedetails.impl.ui.RecipeDetailsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecipeDetailsGroceriesManager @Inject constructor(
    private val checkAddedIngredientsInGroceriesUseCase: CheckAddedIngredientsInGroceriesUseCase,
    private val addAllIngredientsToGroceriesUseCase: AddAllIngredientsToGroceriesUseCase,
    private val addIngredientToGroceriesUseCase: AddIngredientToGroceriesUseCase,
) {
    fun observeCheckedIngredients(
        recipe: FullRecipe,
        scope: CoroutineScope,
        updateState: (RecipeDetailsState.Success.() -> RecipeDetailsState.Success) -> Unit
    ) {
        /*scope.launch {
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
        }*/
    }

    fun addAllIngredientsToShoppingList(
        recipeName: String,
        ingredients: List<IngredientSection>,
        scope: CoroutineScope
    ) {
        scope.launch {
            addAllIngredientsToGroceriesUseCase(
                recipeName = recipeName,
                ingredients = ingredients
            )
        }
    }

    fun addIngredientToShoppingList(
        recipeName: String,
        ingredient: Ingredient,
        scope: CoroutineScope
    ) {
        scope.launch {
            addIngredientToGroceriesUseCase(
                recipeName = recipeName,
                ingredient = ingredient
            )
        }
    }
}