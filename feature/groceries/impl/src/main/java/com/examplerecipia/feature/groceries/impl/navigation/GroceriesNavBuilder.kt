package com.examplerecipia.feature.groceries.impl.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.recipia.feature.groceries.api.GroceriesRoutingContract
import com.examplerecipia.feature.groceries.impl.GroceriesScreen

fun NavGraphBuilder.groceriesScreen() {
    composable<GroceriesRoutingContract.Groceries> {
        GroceriesScreen()
    }
}