package com.example.recipia

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.recipia.feature.recipedetails.impl.navigation.recipeDetailsScreen
import recipia.feature.add_recipe.impl.navigation.addRecipeScreen
import recipia.feature.main_screen.api.MainScreenRoutingContract
import recipia.feature.main_screen.impl.mainScreen

@Composable
fun NavigationHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MainScreenRoutingContract.MainScreen
    ) {
        mainScreen(navController)
        recipeDetailsScreen()
        addRecipeScreen(navController)
    }
}