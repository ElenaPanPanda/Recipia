package com.examplerecipia.feature.groceries.impl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.recipia.feature.groceries.api.GroceriesRoutingContract
import com.examplerecipia.feature.groceries.impl.ui.GroceriesScreen

fun NavGraphBuilder.groceriesScreen() {
    composable<GroceriesRoutingContract.Groceries> {
        GroceriesScreen()
    }
}