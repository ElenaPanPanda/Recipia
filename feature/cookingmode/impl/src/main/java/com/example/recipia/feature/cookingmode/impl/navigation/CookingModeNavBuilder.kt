package com.example.recipia.feature.cookingmode.impl.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.recipia.feature.cookingmode.api.CookingModeRoutingContract
import com.example.recipia.feature.cookingmode.impl.ui.CookingModeScreen

fun NavGraphBuilder.cookingModeScreen(onNavigateBack: () -> Unit) {
    composable<CookingModeRoutingContract.CookingMode> {
        val route = it.toRoute<CookingModeRoutingContract.CookingMode>()
        CookingModeScreen(
            recipeId = route.recipeId,
        )
    }
}
