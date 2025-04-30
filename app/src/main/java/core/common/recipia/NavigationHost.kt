package core.common.recipia

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import recipia.feature.recipe_list_api.RecipeListRoutingContract
import recipia.feature.recipe_list_impl.recipeListScreen

@Composable
fun NavigationHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RecipeListRoutingContract.RecipeList
    ) {
        recipeListScreen(navController)
    }
}