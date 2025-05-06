package recipia.feature.recipe_list_impl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import recipia.feature.recipe_list_api.RecipeListRoutingContract
import recipia.feature.recipe_list_impl.ui.RecipeListScreen

fun NavGraphBuilder.recipeListScreen(navController: NavHostController) {
    composable<RecipeListRoutingContract.RecipeList> {
        RecipeListScreen()
    }
}