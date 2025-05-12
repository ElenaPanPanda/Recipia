package com.example.recipia.feature.recipedetails.impl.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.recipia.feature.recipedetails.api.RecipeDetailsRoutingContract
import com.example.recipia.feature.recipedetails.impl.ui.RecipeDetailsScreen

fun NavGraphBuilder.recipeDetailsScreen() {
    composable<RecipeDetailsRoutingContract.RecipeDetails> {
        val route = it.toRoute<RecipeDetailsRoutingContract.RecipeDetails>()
        RecipeDetailsScreen(recipeId = route.recipeId)
    }
}